package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.ErrataDTO;
import ch.zkmf2024.server.repository.ErrataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.zkmf2024.server.repository.ErrataRepository.COMPARATOR;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Slf4j
@Service
public class ErrataService {
    private final ErrataRepository errataRepository;

    public ErrataService(ErrataRepository errataRepository) {
        this.errataRepository = errataRepository;
    }

    public List<ErrataDTO> findAll() {
        return errataRepository.findAll().stream()
                               .sorted(COMPARATOR)
                               .toList();
    }

    public void update(List<ErrataDTO> dtos) {
        var texts = dtos.stream()
                        .filter(dto -> isNotBlank(dto.text()))
                        .collect(toMap(ErrataDTO::id, ErrataDTO::text));

        errataRepository.findAllPojos().forEach(pojo -> {
            pojo.setText(texts.get(pojo.getId()));
            errataRepository.update(pojo);
        });
    }
}
