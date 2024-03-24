package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.FestprogrammDayDTO;
import ch.zkmf2024.server.dto.FestprogrammEntryDTO;
import ch.zkmf2024.server.repository.FestprogrammRepository;
import ch.zkmf2024.server.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                                           .map(pojo -> new FestprogrammEntryDTO(
                                                   LocalDateTime.of(pojo.getDate(), pojo.getStartTime()),
                                                   pojo.getDescription(),
                                                   pojo.getLocation(),
                                                   pojo.getImportant()
                                           ))
                                           .sorted(comparing(FestprogrammEntryDTO::start))
                                           .collect(groupingBy(FestprogrammEntryDTO::getDate, toList()));

        return perDay.keySet().stream()
                     .sorted()
                     .map(day -> new FestprogrammDayDTO(FormatUtil.formatDate(day, true), perDay.get(day)))
                     .toList();
    }
}
