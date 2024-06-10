package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.MessageFavoriteDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.NotificationSentPojo;
import ch.zkmf2024.server.repository.NotificationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.util.DateUtil;
import ch.zkmf2024.server.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.Nullable;
import java.time.temporal.ChronoUnit;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toSet;

@Slf4j
@Service
public class NotificationService {

    private final TimetableRepository timetableRepository;
    private final NotificationRepository notificationRepository;
    private final FirebaseMessagingService firebaseMessagingService;

    public NotificationService(TimetableRepository timetableRepository,
                               NotificationRepository notificationRepository,
                               @Nullable FirebaseMessagingService firebaseMessagingService) {
        this.timetableRepository = timetableRepository;
        this.notificationRepository = notificationRepository;
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @Scheduled(timeUnit = MINUTES, initialDelay = 1, fixedRate = 1)
    public void sendVereinNotifications() {
        if (firebaseMessagingService == null) {
            log.info("firebaseMessagingService not initialized");
            return;
        }

        log.info("looking for upcoming verein timetable entries in the next 30 minutes...");
        var upcomingEntries = timetableRepository.findAllUpcomingIn(30, ChronoUnit.MINUTES);
        var sent = notificationRepository.findAll().stream().map(NotificationSentPojo::getId).collect(toSet());

        upcomingEntries.stream()
                       .filter(dto -> !sent.contains(dto.getId()))
                       .forEach(dto -> {
                           var message = "Dein Favorit spielt um %s Uhr%n%s".formatted(FormatUtil.formatTime(dto.startTime()), dto.location());
                           firebaseMessagingService.send(new MessageFavoriteDTO(dto.vereinIdentifier(),
                                                                                dto.vereinName(),
                                                                                message));
                           notificationRepository.insert(new NotificationSentPojo(dto.getId(), DateUtil.now()));
                           log.info("sent notification: {}", message);
                       });
    }
}
