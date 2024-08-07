package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.CurrentTimetablePreviewDTO;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.TimetableDayOverviewDTO;
import ch.zkmf2024.server.dto.TimetableOverviewEntryDTO;
import ch.zkmf2024.server.dto.TimetablePreviewDTO;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO.TimeTableEntryDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.UnterhaltungRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.dto.TimetableEntryType.BESPRECHUNG;
import static ch.zkmf2024.server.dto.TimetableEntryType.EINSPIEL;
import static ch.zkmf2024.server.dto.TimetableEntryType.MARSCHMUSIK;
import static ch.zkmf2024.server.dto.TimetableEntryType.PLATZKONZERT;
import static ch.zkmf2024.server.dto.TimetableEntryType.WETTSPIEL;
import static ch.zkmf2024.server.util.DateUtil.currentTime;
import static ch.zkmf2024.server.util.FormatUtil.formatDate;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final LocationRepository locationRepository;
    private final VereinRepository vereinRepository;
    private final UnterhaltungRepository unterhaltungRepository;
    private final SponsoringService sponsoringService;
    private final EmergencyService emergencyService;
    private final ScreenService screenService;

    public TimetableService(TimetableRepository timetableRepository,
                            LocationRepository locationRepository,
                            VereinRepository vereinRepository,
                            UnterhaltungRepository unterhaltungRepository,
                            SponsoringService sponsoringService,
                            EmergencyService emergencyService,
                            ScreenService screenService) {
        this.timetableRepository = timetableRepository;
        this.locationRepository = locationRepository;
        this.vereinRepository = vereinRepository;
        this.unterhaltungRepository = unterhaltungRepository;
        this.sponsoringService = sponsoringService;
        this.emergencyService = emergencyService;
        this.screenService = screenService;
    }

    public List<TimetableEntryDTO> findAll() {
        return timetableRepository.findAllDTOs();
    }

    public List<VereinSelectionDTO> findVereine() {
        return vereinRepository.findAllNotYetPlanned();
    }

    @Transactional
    public void create(Long vereinId, List<TimetableEntryCreateDTO> dtos) {
        var allExisting = timetableRepository.findAll();

        // find collision with existing ones
        var collision = dtos.stream()
                            .map(dto -> findCollision(dto, allExisting))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .findFirst();

        if (collision.isPresent()) {
            var collidingEntry = collision.get();
            throw new IllegalArgumentException("Es gibt Überschneidung mit bestehendem Eintrag: %s %s-%s".formatted(
                    collidingEntry.getDate(),
                    collidingEntry.getStartTime(),
                    collidingEntry.getEndTime()));
        }

        var pojos = new ArrayList<TimetableEntryPojo>();
        for (var dto : dtos) {
            for (var entry : dto.entries()) {
                pojos.add(new TimetableEntryPojo(
                        null,
                        vereinId,
                        dto.vereinProgrammId(),
                        entry.locationId(),
                        entry.date(),
                        entry.start(),
                        entry.end(),
                        TimetableEntryType.lookupLiteral(entry.type().name())
                ));
            }
        }
        timetableRepository.insertAll(pojos);
    }

    @Transactional
    public void deleteEntry(Long id) {
        timetableRepository.delete(id);
    }

    protected Optional<TimetableEntryPojo> findCollision(TimetableEntryCreateDTO dto, List<TimetableEntryPojo> allExisting) {
        return dto.entries().stream()
                  .map(entry -> findCollision(entry, allExisting))
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .findFirst();
    }

    protected Optional<TimetableEntryPojo> findCollision(TimeTableEntryDTO entry, List<TimetableEntryPojo> allExisting) {
        for (var timetableEntryDTO : allExisting) {
            // same location, same date and overlapping times
            if (timetableEntryDTO.getFkLocation().equals(entry.locationId()) &&
                    timetableEntryDTO.getDate().equals(entry.date()) &&
                    (timetableEntryDTO.getStartTime().isBefore(entry.end()) && timetableEntryDTO.getEndTime().isAfter(entry.start()))) {

                return Optional.of(timetableEntryDTO);
            }
        }

        return Optional.empty();
    }

    public List<TimetableEntryCreateDTO> findProgrammeByVerein(Long vereinId) {
        var wettspiel = locationRepository.findAllSelectionByType(LocationType.WETTSPIELLOKAL);
        var einspiel = locationRepository.findAllSelectionByType(LocationType.EINSPIELLOKAL);
        var juryfeedback = locationRepository.findAllSelectionByType(LocationType.JURYFEEDBACK);
        var parademusik = locationRepository.findAllSelectionByType(LocationType.PARADEMUSIK);
        var platzkonzert = locationRepository.findAllSelectionByType(LocationType.PLATZKONZERT);

        // hardcoded to Saturday of event
        var defaultDate = LocalDate.of(2024, 6, 22);
        return vereinRepository.findProgrammeSelection(vereinId).stream()
                               .map(it -> new TimetableEntryCreateDTO(
                                       it.id(),
                                       it.modul(),
                                       it.modulDescription(),
                                       it.klasse(),
                                       it.besetzung(),
                                       switch (it.modul()) {
                                           case A, B, G, H -> List.of(
                                                   new TimeTableEntryDTO(EINSPIEL, null, einspiel, defaultDate, null, null),
                                                   new TimeTableEntryDTO(WETTSPIEL, null, wettspiel, defaultDate, null, null),
                                                   new TimeTableEntryDTO(BESPRECHUNG, null, juryfeedback, defaultDate, null, null)
                                           );
                                           case C -> List.of(
                                                   new TimeTableEntryDTO(PLATZKONZERT, null, platzkonzert, defaultDate, null, null)
                                           );
                                           case D -> List.of(
                                                   new TimeTableEntryDTO(MARSCHMUSIK, null, parademusik, defaultDate, null, null)
                                           );
                                           case E, F -> List.of();
                                       }
                               ))
                               .toList();
    }

    @Cacheable("timetables")
    public List<TimetableDayOverviewDTO> getPublicTimetable() {
        return getPublicTimetable(null);
    }

    @Cacheable("timetables-location")
    public List<TimetableDayOverviewDTO> getPublicTimetable(String locationIdentifier) {
        var entriesPerDate = timetableRepository.findAllForPublic().stream()
                                                .filter(dto -> locationIdentifier == null || StringUtils.equals(dto.location().identifier(), locationIdentifier))
                                                .collect(groupingBy(TimetableOverviewEntryDTO::date, toList()));

        var result = new ArrayList<TimetableDayOverviewDTO>();
        for (var localDate : entriesPerDate.keySet().stream()
                                           .sorted()
                                           .toList()) {
            var entries = entriesPerDate.get(localDate);
            result.add(new TimetableDayOverviewDTO(formatDate(localDate, true),
                                                   // entries.stream().allMatch(TimetableOverviewEntryDTO::inPast),
                                                   false,
                                                   entries.stream()
                                                          .sorted(comparing(TimetableOverviewEntryDTO::start)
                                                                          .thenComparing(TimetableOverviewEntryDTO::end))
                                                          .toList()));
        }

        return result;
    }

    public List<LocationSelectionDTO> findLocationsByType(LocationType type) {
        return locationRepository.findAllSelectionByType(type);
    }

    public void updateEntry(TimetableEntryDTO dto) {
        var entry = timetableRepository.find(dto.id()).orElseThrow();
        entry.setDate(dto.date());
        entry.setStartTime(dto.start());
        entry.setEndTime(dto.end());
        entry.setFkLocation(dto.locationId());
        timetableRepository.update(entry);
    }

    public CurrentTimetablePreviewDTO getCurrentPreview(String locationIdentifier) {
        var emergencyMessage = emergencyService.findActiveEmergencyMessage();
        if (emergencyMessage.isPresent()) {
            // emergency has the highest priority
            return CurrentTimetablePreviewDTO.of(emergencyMessage.get());
        }

        var current = timetableRepository.findCurrent(locationIdentifier);
        if (current.isPresent()) {
            // if current one is going on, do not show anything else
            return CurrentTimetablePreviewDTO.of(current.get());
        }

        var next = findNext(locationIdentifier);

        return new CurrentTimetablePreviewDTO(null,
                                              next.orElse(null),
                                              sponsoringService.getRandom(6),
                                              currentTime(),
                                              null,
                                              screenService.findActive(locationIdentifier).orElse(null));
    }

    private Optional<TimetablePreviewDTO> findNext(String locationIdentifier) {
        var nextTimetable = timetableRepository.findNext(locationIdentifier);
        var nextUnterhaltung = unterhaltungRepository.findNext(locationIdentifier);

        if (nextTimetable.isPresent() && nextUnterhaltung.isPresent()) {
            return nextTimetable.get().getStartDateTime().isBefore(nextUnterhaltung.get().getStartDateTime())
                    ? nextTimetable
                    : nextUnterhaltung;
        } else {
            return nextTimetable.or(() -> nextUnterhaltung);
        }
    }

    public Optional<String> getWelcomeScreen() {
        return screenService.getWelcomeScreen();
    }
}
