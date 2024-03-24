package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.FestprogrammDayDTO;
import ch.zkmf2024.server.dto.FestprogrammEntryDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.FestprogrammEntryPojo;
import ch.zkmf2024.server.repository.FestprogrammRepository;
import ch.zkmf2024.server.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class FestprogrammService {

    private final FestprogrammRepository festprogrammRepository;

    public FestprogrammService(FestprogrammRepository festprogrammRepository) {
        this.festprogrammRepository = festprogrammRepository;
    }

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
                     .map(day -> new FestprogrammDayDTO(FormatUtil.formatDate(day, true), perDay.get(day)))
                     .toList();
    }

    private String getTimeFrom(FestprogrammEntryPojo pojo) {
        return StringUtils.contains(pojo.getTimeDescription(), " - ") ? StringUtils.substringBefore(pojo.getTimeDescription(), " - ") : pojo.getTimeDescription();
    }

    private String getTimeTo(FestprogrammEntryPojo pojo) {
        return StringUtils.contains(pojo.getTimeDescription(), " - ") ? StringUtils.substringAfter(pojo.getTimeDescription(), " - ") : null;
    }
}
