package ch.zkmf2024.server.service;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class TimetableServiceTest extends AbstractIntegrationTest {
    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private VereinRepository vereinRepository;

    private TimetableService service;

    @BeforeEach
    void setUp() {
        service = new TimetableService(timetableRepository, locationRepository, vereinRepository);
    }

    @Test
    void create() {
        service.create(123L, List.of());
    }
}
