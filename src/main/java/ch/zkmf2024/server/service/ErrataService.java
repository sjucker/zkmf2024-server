package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.Besetzung;
import ch.zkmf2024.server.dto.Klasse;
import ch.zkmf2024.server.dto.Modul;
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
    private final MailService mailService;

    public ErrataService(ErrataRepository errataRepository, MailService mailService) {
        this.errataRepository = errataRepository;
        this.mailService = mailService;
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

    public void send(Modul modul, Klasse klasse, Besetzung besetzung) {
        mailService.sendErrataMail(errataRepository.getRelevantVereinEmails(modul, klasse, besetzung));
    }
}
