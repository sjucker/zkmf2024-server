package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.AppPageDTO;
import ch.zkmf2024.server.dto.admin.AppPageCreateDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.AppPagePojo;
import ch.zkmf2024.server.repository.AppPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AppPageService {

    private final AppPageRepository appPageRepository;

    public AppPageService(AppPageRepository appPageRepository) {
        this.appPageRepository = appPageRepository;
    }

    public List<AppPageDTO> getAll() {
        return appPageRepository.findAll().stream()
                                .map(pojo -> new AppPageDTO(pojo.getId(), pojo.getMarkdown(), pojo.getCloudflareId()))
                                .toList();
    }

    public void insert(AppPageCreateDTO dto) {
        appPageRepository.insert(new AppPagePojo(null, dto.markdown(), dto.cloudflareId()));
    }

    public void update(Long id, AppPageDTO dto) {
        if (!Objects.equals(id, dto.id())) {
            throw new IllegalArgumentException("ID from path does not match ID in DTO!");
        }
        appPageRepository.update(new AppPagePojo(id, dto.markdown(), dto.cloudflareId()));
    }

    public Optional<AppPageDTO> find(Long id) {
        return appPageRepository.findById(id)
                                .map(pojo -> new AppPageDTO(pojo.getId(), pojo.getMarkdown(), pojo.getCloudflareId()));
    }
}
