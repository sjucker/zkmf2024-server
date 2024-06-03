package ch.zkmf2024.server.service;

import ch.zkmf2024.server.AbstractIntegrationTest;
import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.admin.MessageFavoriteDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NotificationSentPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinProgrammPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.NotificationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import ch.zkmf2024.server.util.DateUtil;
import ch.zkmf2024.server.util.FormatUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

import static ch.zkmf2024.server.dto.Besetzung.HARMONIE;
import static ch.zkmf2024.server.dto.Klasse.KLASSE_1;
import static ch.zkmf2024.server.dto.Modul.A;
import static ch.zkmf2024.server.jooq.generated.enums.TimetableEntryType.WETTSPIEL;
import static ch.zkmf2024.server.util.DateUtil.currentTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class NotificationServiceIntTest extends AbstractIntegrationTest {

    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private VereinRepository vereinRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Mock
    private FirebaseMessagingService firebaseMessagingService;

    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService(timetableRepository, notificationRepository, firebaseMessagingService);
    }

    @Test
    void sendVereinNotifications() {
        // given
        var locations = locationRepository.findAllByType(LocationType.WETTSPIELLOKAL);
        var location1 = locations.getFirst();
        var location2 = locations.getLast();

        var verein1 = createVerein("Verein1");
        var verein2 = createVerein("Verein2");
        var verein3 = createVerein("Verein3");

        var programm1 = createVereinProgramm(verein1.getId());
        var programm2 = createVereinProgramm(verein2.getId());
        var programm3 = createVereinProgramm(verein3.getId());

        createTimetableEntry(verein1.getId(), programm1.getId(), location1.id(), currentTime().plusMinutes(40));
        var startTime = currentTime().plusMinutes(25);
        createTimetableEntry(verein2.getId(), programm2.getId(), location2.id(), startTime);
        var timetableEntry3 = createTimetableEntry(verein3.getId(), programm3.getId(), location2.id(), currentTime().plusMinutes(10));

        notificationRepository.insert(new NotificationSentPojo("%s-%s".formatted(timetableEntry3.getId(), verein3.getIdentifier()), DateUtil.now()));

        // when
        notificationService.sendVereinNotifications();
        // call it again to make sure it is only sent once
        notificationService.sendVereinNotifications();

        var captor = ArgumentCaptor.forClass(MessageFavoriteDTO.class);
        verify(firebaseMessagingService, times(1)).send(captor.capture());
        assertThat(captor.getValue()).satisfies(dto -> {
            assertThat(dto.identifier()).isEqualTo(verein2.getIdentifier());
            assertThat(dto.title()).isEqualTo(verein2.getVereinsname());
            assertThat(dto.body()).isEqualTo("Um %s spielt Verein2 (Embrisaal)".formatted(FormatUtil.formatTime(startTime)));
        });
    }

    private TimetableEntryPojo createTimetableEntry(Long vereinId, Long programmId, Long locationId, LocalTime startTime) {
        var timetableEntry = new TimetableEntryPojo(null, vereinId, programmId, locationId,
                                                    DateUtil.today(), startTime, startTime.plusMinutes(30), WETTSPIEL);
        timetableRepository.insert(timetableEntry);
        return timetableEntry;
    }

    private VereinProgrammPojo createVereinProgramm(Long vereinId) {
        var programm = new VereinProgrammPojo(null, vereinId, A.name(), KLASSE_1.name(), HARMONIE.name(), null, null, null, null, null, null, null, null, null, false, false, false, false, false, null, null, null, null, null, null);
        vereinRepository.insert(programm);
        return programm;
    }

    private VereinPojo createVerein(String vereinsname) {
        var kontakt1 = new KontaktPojo();
        vereinRepository.insert(kontakt1);
        var kontakt2 = new KontaktPojo();
        vereinRepository.insert(kontakt2);

        var verein = new VereinPojo();
        verein.setVereinsname(vereinsname);
        verein.setEmail(vereinsname);
        verein.setDirektionKontaktId(kontakt1.getId());
        verein.setPraesidentKontaktId(kontakt2.getId());
        verein.setIdentifier(vereinsname);
        vereinRepository.insert(verein);
        return verein;
    }

}
