package ch.zkmf2024.server.rest.secured;

import ch.zkmf2024.server.dto.UserRole;
import ch.zkmf2024.server.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured/auth")
public class SecuredAuthEndpoint {

    private final UserRepository userRepository;

    public SecuredAuthEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<UserRole> authorize(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userRepository.findById(userDetails.getUsername())
                                               .map(pojo -> UserRole.valueOf(pojo.getRole()))
                                               .orElseThrow());
    }
}
