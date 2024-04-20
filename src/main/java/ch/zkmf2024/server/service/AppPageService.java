package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.AppPageDTO;
import ch.zkmf2024.server.dto.admin.AppPageCreateDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.AppPagePojo;
import ch.zkmf2024.server.repository.AppPageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Slf4j
@Service
public class AppPageService {

    private final AppPageRepository appPageRepository;

    public AppPageService(AppPageRepository appPageRepository) {
        this.appPageRepository = appPageRepository;
    }

    public List<AppPageDTO> getAll() {
        return appPageRepository.findAll().stream()
                                .map(AppPageService::toDTO)
                                .toList();
    }

    public void insert(AppPageCreateDTO dto) {
        appPageRepository.insert(new AppPagePojo(null, dto.markdown(), defaultIfBlank(dto.cloudflareId(), null), dto.title(), dto.news(), LocalDateTime.now()));
    }

    public void update(Long id, AppPageDTO dto) {
        if (!Objects.equals(id, dto.id())) {
            throw new IllegalArgumentException("ID from path does not match ID in DTO!");
        }
        var pojo = appPageRepository.findById(id).orElseThrow();
        pojo.setMarkdown(dto.markdown());
        pojo.setCloudflareId(defaultIfBlank(dto.cloudflareId(), null));
        pojo.setTitle(dto.title());
        pojo.setNews(dto.news());

        appPageRepository.update(pojo);
    }

    public Optional<AppPageDTO> find(Long id) {
        return appPageRepository.findById(id)
                                .map(AppPageService::toDTO);
    }

    public List<AppPageDTO> getNews() {
        return appPageRepository.findAll().stream()
                                .filter(AppPagePojo::getNews)
                                .map(AppPageService::toDTO)
                                .sorted(comparing(AppPageDTO::id).reversed())
                                .toList();
    }

    private static AppPageDTO toDTO(AppPagePojo pojo) {
        return new AppPageDTO(pojo.getId(), pojo.getMarkdown(), pojo.getTitle(), pojo.getCreatedAt().toLocalDate(), pojo.getNews(), pojo.getCloudflareId());
    }
}
