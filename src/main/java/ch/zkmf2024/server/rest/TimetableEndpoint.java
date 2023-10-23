package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.service.TimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/timetable")
public class TimetableEndpoint {

    private final TimetableService timetableService;

    public TimetableEndpoint(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

}
