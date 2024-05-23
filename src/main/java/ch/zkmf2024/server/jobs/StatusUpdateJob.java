package ch.zkmf2024.server.jobs;

import ch.zkmf2024.server.dto.PhaseStatus;
import ch.zkmf2024.server.service.VereinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
@Service
public class StatusUpdateJob {

    private final VereinService vereinService;

    public StatusUpdateJob(VereinService vereinService) {
        this.vereinService = vereinService;
    }

    @Scheduled(timeUnit = MINUTES, initialDelay = 3, fixedRate = 60)
    public void updateStatus() {
        log.info("running status update job");

        for (var id : vereinService.findAllVereinIds()) {
            var verein = vereinService.findById(id).orElseThrow();
            var status = vereinService.findStatus(id);

            if (PhaseStatus.valueOf(status.getPhase1()) != verein.getPhase1Status() ||
                    PhaseStatus.valueOf(status.getPhase2()) != verein.getPhase2Status() ||
                    PhaseStatus.valueOf(status.getPhase4()) != verein.getPhase4Status()) {
                log.info("updating status of verein with id={}, old phase 1={}, new phase 1={}, old phase 2={}, new phase 2={}, old phase 4={}, new phase 4={}",
                         id, status.getPhase1(), verein.getPhase1Status(), status.getPhase2(), verein.getPhase2Status(), status.getPhase4(), verein.getPhase4Status());

                vereinService.updateStatus(id, verein);
            }
        }

        log.info("status update job finished");
    }
}
