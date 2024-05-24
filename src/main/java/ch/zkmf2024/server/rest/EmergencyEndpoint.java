package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.EmergencyMessageDTO;
import ch.zkmf2024.server.service.EmergencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/emergency")
public class EmergencyEndpoint {

    private final EmergencyService emergencyService;

    public EmergencyEndpoint(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping
    public ResponseEntity<EmergencyMessageDTO> getEmergencyMessages() {
        log.info("GET /public/emergency");
        return ResponseEntity.of(emergencyService.findActiveEmergencyMessage());
    }
}
