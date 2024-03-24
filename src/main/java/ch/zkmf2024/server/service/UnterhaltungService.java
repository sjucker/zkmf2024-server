package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.LocationDTO;
import ch.zkmf2024.server.dto.TimetableOverviewEntryDTO;
import ch.zkmf2024.server.dto.UnterhaltungEntryType;
import ch.zkmf2024.server.dto.UnterhaltungTypeDTO;
import ch.zkmf2024.server.dto.UnterhaltungsEntryDTO;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.UnterhaltungRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

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

    public List<UnterhaltungTypeDTO> get() {
        var locationPerId = locationRepository.findAll().stream()
                                              .collect(toMap(LocationDTO::id, identity()));

        var perType = unterhaltungRepository.findAll().stream()
                                            .map(pojo -> new UnterhaltungsEntryDTO(
                                                    UnterhaltungEntryType.from(pojo.getEntryType()),
                                                    pojo.getDate(),
                                                    pojo.getStartTime(),
                                                    pojo.getEndTime(),
                                                    pojo.getTitle(),
                                                    pojo.getSubtitle(),
                                                    locationPerId.get(pojo.getFkLocation()),
                                                    pojo.getCloudflareId(),
                                                    null,
                                                    pojo.getIdentifier()
                                            ))
                                            .collect(groupingBy(UnterhaltungsEntryDTO::type, toCollection(ArrayList::new)));

        for (var platzkonzert : timetableRepository.findAllPlatzkonzzerte()) {
            var dto = new UnterhaltungsEntryDTO(
                    getType(platzkonzert),
                    platzkonzert.date(),
                    platzkonzert.start(),
                    platzkonzert.end(),
                    platzkonzert.vereinsname(),
                    "Unterhaltungskonzert",
                    platzkonzert.location(),
                    null,
                    platzkonzert.vereinIdentifier(),
                    null
            );
            perType.get(dto.type()).add(dto);
        }

        return perType.keySet().stream()
                      .map(type -> new UnterhaltungTypeDTO(type, perType.get(type).stream()
                                                                        .sorted(comparing(UnterhaltungsEntryDTO::date).thenComparing(UnterhaltungsEntryDTO::start))
                                                                        .toList()))
                      .toList();
    }

    private UnterhaltungEntryType getType(TimetableOverviewEntryDTO platzkonzert) {
        return switch (platzkonzert.date().getDayOfWeek()) {
            case SATURDAY -> UnterhaltungEntryType.SAMSTAG_TAG;
            case SUNDAY -> UnterhaltungEntryType.SONNTAG;
            default -> throw new IllegalStateException("only saturday or sunday possible!");
        };
    }
}
