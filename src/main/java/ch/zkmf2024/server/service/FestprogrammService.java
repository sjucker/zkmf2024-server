package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.FestprogrammDayDTO;
import ch.zkmf2024.server.dto.FestprogrammEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.FestprogrammEntryPojo;
import ch.zkmf2024.server.repository.FestprogrammRepository;
import ch.zkmf2024.server.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.contains;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

@Slf4j
@Service
public class FestprogrammService {

    private static final String TIME_SEPARATOR = " - ";
    private final FestprogrammRepository festprogrammRepository;

    public FestprogrammService(FestprogrammRepository festprogrammRepository) {
        this.festprogrammRepository = festprogrammRepository;
    }

    @Cacheable("festprogramm")
    public List<FestprogrammDayDTO> get() {
        var perDay = festprogrammRepository.findAll().stream()
                                           .sorted(comparing(FestprogrammEntryPojo::getDate).thenComparing(FestprogrammEntryPojo::getStartTime))
                                           .map(pojo -> new FestprogrammEntryDTO(
                                                   pojo.getDate(),
                                                   getTimeFrom(pojo),
                                                   getTimeTo(pojo),
                                                   pojo.getDescription(),
                                                   pojo.getLocation(),
                                                   pojo.getImportant()
                                           ))
                                           .collect(groupingBy(FestprogrammEntryDTO::date, toList()));

        return perDay.keySet().stream()
                     .sorted()
                     .map(day -> new FestprogrammDayDTO(FormatUtil.formatDateWritten(day),
                                                        // day.isBefore(today()),
                                                        false,
                                                        perDay.get(day)))
                     .toList();
    }

    private String getTimeFrom(FestprogrammEntryPojo pojo) {
        return contains(pojo.getTimeDescription(), TIME_SEPARATOR) ? substringBefore(pojo.getTimeDescription(), TIME_SEPARATOR) + TIME_SEPARATOR : pojo.getTimeDescription();
    }

    private String getTimeTo(FestprogrammEntryPojo pojo) {
        return contains(pojo.getTimeDescription(), TIME_SEPARATOR) ? substringAfter(pojo.getTimeDescription(), TIME_SEPARATOR) : null;
    }
}
