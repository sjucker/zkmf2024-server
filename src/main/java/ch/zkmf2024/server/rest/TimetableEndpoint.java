package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.TimetableDayOverviewDTO;
import ch.zkmf2024.server.service.TimetableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/public/timetable")
public class TimetableEndpoint {

    private final TimetableService timetableService;

    public TimetableEndpoint(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping
    public ResponseEntity<List<TimetableDayOverviewDTO>> getTimetable() {
        log.info("GET /public/timetable");
        
        return ResponseEntity.ok(timetableService.getPublicTimetable());
    }

}
