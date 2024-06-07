package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.UnterhaltungEntryType;
import ch.zkmf2024.server.dto.UnterhaltungTypeDTO;
import ch.zkmf2024.server.dto.UnterhaltungsEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.UnterhaltungEntryPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.TimetableRepository.ModulKlasseBesetzung;
import ch.zkmf2024.server.repository.UnterhaltungRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.zkmf2024.server.dto.Besetzung.BRASS_BAND;
import static ch.zkmf2024.server.dto.Besetzung.HARMONIE;
import static ch.zkmf2024.server.dto.Klasse.HOECHSTKLASSE;
import static ch.zkmf2024.server.dto.Klasse.KLASSE_1;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.util.DateUtil.now;
import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.replace;

@Slf4j
@Service
public class UnterhaltungService {

    private final UnterhaltungRepository unterhaltungRepository;
    private final LocationRepository locationRepository;
    private final TimetableRepository timetableRepository;

    public UnterhaltungService(UnterhaltungRepository unterhaltungRepository,
                               LocationRepository locationRepository,
                               TimetableRepository timetableRepository) {
        this.unterhaltungRepository = unterhaltungRepository;
        this.locationRepository = locationRepository;
        this.timetableRepository = timetableRepository;
    }

    @Cacheable("unterhaltung")
    public List<UnterhaltungTypeDTO> get() {
        var locationPerId = locationRepository.findAll().stream()
                                              .collect(toMap(LocationDTO::id, identity()));

        var perType = unterhaltungRepository.findAll().stream()
                                            .map(pojo -> toUnterhaltungsEntryDTO(pojo, locationPerId.get(pojo.getFkLocation())))
                                            .collect(groupingBy(UnterhaltungsEntryDTO::type, toCollection(ArrayList::new)));

        var now = now();
        for (var platzkonzert : timetableRepository.findAllPlatzkonzzerte()) {
            var dto = new UnterhaltungsEntryDTO(
                    switch (platzkonzert.date().getDayOfWeek()) {
                        case SATURDAY -> UnterhaltungEntryType.SAMSTAG_TAG;
                        case SUNDAY -> UnterhaltungEntryType.SONNTAG;
                        default -> throw new IllegalStateException("only saturday or sunday possible!");
                    },
                    platzkonzert.date(),
                    platzkonzert.start(),
                    platzkonzert.end(),
                    platzkonzert.vereinsname(),
                    "Unterhaltungskonzert",
                    null,
                    platzkonzert.location(),
                    null,
                    platzkonzert.vereinIdentifier(),
                    null,
                    now.isAfter(LocalDateTime.of(platzkonzert.date(), platzkonzert.end()))
            );
            perType.get(dto.type()).add(dto);
        }

        for (var verein : timetableRepository.find(List.of(new ModulKlasseBesetzung(A, HOECHSTKLASSE, HARMONIE),
                                                           new ModulKlasseBesetzung(A, KLASSE_1, BRASS_BAND)))) {
            var dto = new UnterhaltungsEntryDTO(
                    switch (verein.date().getDayOfWeek()) {
                        case SATURDAY -> UnterhaltungEntryType.SAMSTAG_ABEND;
                        case SUNDAY -> UnterhaltungEntryType.SONNTAG;
                        default -> throw new IllegalStateException("only saturday or sunday possible!");
                    },
                    verein.date(),
                    verein.start(),
                    verein.end(),
                    verein.vereinsname(),
                    replace(verein.competition(), "Konzertmusik,", "Wettspiel"),
                    null,
                    verein.location(),
                    null,
                    verein.vereinIdentifier(),
                    null,
                    now.isAfter(LocalDateTime.of(verein.date(), verein.end()))
            );
            perType.get(dto.type()).add(dto);
        }

        return perType.keySet().stream()
                      .map(type -> {
                          var entries = perType.get(type);
                          return new UnterhaltungTypeDTO(type,
                                                         entries.stream().allMatch(e -> now.isAfter(LocalDateTime.of(e.date(), e.end()))),
                                                         entries.stream()
                                                                .sorted(comparing(UnterhaltungsEntryDTO::date)
                                                                                .thenComparing(UnterhaltungsEntryDTO::start)
                                                                                .thenComparing(UnterhaltungsEntryDTO::unterhaltungIdentifier, nullsFirst(naturalOrder())))
                                                                .toList());
                      })
                      .toList();
    }

    private UnterhaltungsEntryDTO toUnterhaltungsEntryDTO(UnterhaltungEntryPojo pojo, LocationDTO location) {
        return new UnterhaltungsEntryDTO(
                UnterhaltungEntryType.from(pojo.getEntryType()),
                pojo.getDate(),
                pojo.getStartTime(),
                pojo.getEndTime(),
                pojo.getTitle(),
                pojo.getSubtitle(),
                pojo.getText(),
                location,
                pojo.getCloudflareId(),
                null,
                pojo.getIdentifier(),
                now().isAfter(LocalDateTime.of(pojo.getDate(), pojo.getEndTime()))
        );
    }

    @Cacheable("unterhaltung-id")
    public Optional<UnterhaltungsEntryDTO> getByUnterhaltungIdentifier(String identifier) {
        return unterhaltungRepository.findByUnterhaltungIdentifier(identifier)
                                     .map(pojo -> toUnterhaltungsEntryDTO(pojo, locationRepository.findById(pojo.getFkLocation())
                                                                                                  .orElseThrow()));
    }
}
