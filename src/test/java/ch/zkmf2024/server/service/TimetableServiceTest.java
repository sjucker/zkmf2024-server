package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.TimetableEntryType;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.UnterhaltungRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    @Mock
    private TimetableRepository timetableRepository;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private VereinRepository vereinRepository;
    @Mock
    private UnterhaltungRepository unterhaltungRepository;

    private TimetableService service;

    @BeforeEach
    void setUp() {
        service = new TimetableService(timetableRepository, locationRepository, vereinRepository, unterhaltungRepository);
    }

    @Test
    void findCollision() {
        assertThat(service.findCollision(createDto(1L, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(10, 30)),
                                         List.of(
                                                 createPojo(1L, LocalDate.now(), LocalTime.of(10, 30), LocalTime.of(11, 0)),
                                                 createPojo(1L, LocalDate.now(), LocalTime.of(9, 30), LocalTime.of(10, 0)),
                                                 createPojo(2L, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(10, 30)),
                                                 createPojo(1L, LocalDate.now().plusDays(1), LocalTime.of(10, 0), LocalTime.of(10, 30))
                                         ))).isEmpty();

        assertThat(service.findCollision(createDto(1L, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(10, 30)),
                                         List.of(createPojo(1L, LocalDate.now(), LocalTime.of(10, 15), LocalTime.of(11, 0))))).isNotEmpty();
    }

    private TimetableEntryPojo createPojo(long locationId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return new TimetableEntryPojo(null, null, null, locationId, date, startTime, endTime, null);
    }

    private TimetableEntryCreateDTO.TimeTableEntryDTO createDto(Long locationId, LocalDate date, LocalTime start, LocalTime end) {
        return new TimetableEntryCreateDTO.TimeTableEntryDTO(TimetableEntryType.WETTSPIEL, locationId, List.of(), date, start, end);
    }

}
