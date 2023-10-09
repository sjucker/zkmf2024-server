package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO.TimeTableEntryDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.dto.LocationType.EINSPIELLOKAL;
import static ch.zkmf2024.server.dto.TimetableEntryType.BESPRECHUNG;
import static ch.zkmf2024.server.dto.TimetableEntryType.EINSPIEL;
import static ch.zkmf2024.server.dto.TimetableEntryType.MARSCHMUSIK;
import static ch.zkmf2024.server.dto.TimetableEntryType.WETTSPIEL;

@Slf4j
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final LocationRepository locationRepository;
    private final VereinRepository vereinRepository;

    public TimetableService(TimetableRepository timetableRepository,
                            LocationRepository locationRepository,
                            VereinRepository vereinRepository) {
        this.timetableRepository = timetableRepository;
        this.locationRepository = locationRepository;
        this.vereinRepository = vereinRepository;
    }

    public List<TimetableEntryDTO> findAll() {
        return timetableRepository.findAllDTOs();
    }

    public List<VereinSelectionDTO> findVereine() {
        return vereinRepository.findAllNotYetPlanned();
    }

    public void create(Long vereinId, List<TimetableEntryCreateDTO> dtos) {
        var allExisting = timetableRepository.findAll();
        // first, validate it
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

        for (var dto : dtos) {
            for (var entry : dto.entries()) {
                timetableRepository.insert(new TimetableEntryPojo(
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
    }

    private Optional<TimetableEntryPojo> findCollision(TimetableEntryCreateDTO dto, List<TimetableEntryPojo> allExisting) {
        return dto.entries().stream()
                  .map(entry -> findCollision(entry, allExisting))
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .findFirst();
    }

    private Optional<TimetableEntryPojo> findCollision(TimeTableEntryDTO entry, List<TimetableEntryPojo> allExisting) {
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
        var einspiel = locationRepository.findAllSelectionByType(EINSPIELLOKAL);
        var juryfeedback = locationRepository.findAllSelectionByType(LocationType.JURYFEEDBACK);
        var parademusik = locationRepository.findAllSelectionByType(LocationType.PARADEMUSIK);

        var defaultDate = LocalDate.of(2024, 6, 22);
        return vereinRepository.findProgrammeSelection(vereinId).stream()
                               .map(it -> new TimetableEntryCreateDTO(
                                       it.id(),
                                       it.modul(),
                                       it.modulDescription(),
                                       it.klasse(),
                                       it.besetzung(),
                                       switch (it.modul()) {
                                           case A, B -> List.of(
                                                   new TimeTableEntryDTO(EINSPIEL, null, einspiel, defaultDate, null, null),
                                                   new TimeTableEntryDTO(WETTSPIEL, null, wettspiel, defaultDate, null, null),
                                                   new TimeTableEntryDTO(BESPRECHUNG, null, juryfeedback, defaultDate, null, null)
                                           );
                                           case C -> List.of(); // TODO Platzkonzert Locations
                                           case D -> List.of(
                                                   new TimeTableEntryDTO(MARSCHMUSIK, null, parademusik, defaultDate, null, null)
                                           );
                                           // TODO rest
                                           default -> List.of();
                                       }
                               ))
                               .toList();
    }

}
