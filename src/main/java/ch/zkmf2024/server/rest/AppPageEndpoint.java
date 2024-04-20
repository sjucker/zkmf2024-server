package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.AppPageDTO;
import ch.zkmf2024.server.service.AppPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public/app-page")
public class AppPageEndpoint {

    private final AppPageService appPageService;

    public AppPageEndpoint(AppPageService appPageService) {
        this.appPageService = appPageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppPageDTO> get(@PathVariable long id) {
        log.info("GET /public/app-page/{}", id);

        return ResponseEntity.of(appPageService.find(id));
    }

    @GetMapping("/news")
    public ResponseEntity<List<AppPageDTO>> news() {
        log.info("GET /public/app-page/news");

        return ResponseEntity.ok(appPageService.getNews());
    }
}
