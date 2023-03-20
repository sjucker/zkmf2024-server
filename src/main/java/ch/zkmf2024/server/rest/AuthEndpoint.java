package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.LoginRequestDTO;
import ch.zkmf2024.server.dto.LoginResponseDTO;
import ch.zkmf2024.server.dto.RegisterHelperRequestDTO;
import ch.zkmf2024.server.dto.RegisterNewsletterRequestDTO;
import ch.zkmf2024.server.dto.UserRole;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.security.JwtService;
import ch.zkmf2024.server.service.HelperRegistrationService;
import ch.zkmf2024.server.service.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static ch.zkmf2024.server.service.DateUtil.now;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    private final UserRepository userRepository;
    private final NewsletterService newsletterService;
    private final HelperRegistrationService helperRegistrationService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthEndpoint(UserRepository userRepository,
                        NewsletterService newsletterService,
                        HelperRegistrationService helperRegistrationService,
                        JwtService jwtService,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.newsletterService = newsletterService;
        this.helperRegistrationService = helperRegistrationService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        log.info("POST /public/auth {}", request.email());

        var user = userRepository.findById(request.email()).orElse(null);

        if (user != null) {
            if (passwordEncoder.matches(request.password(), user.getPassword())) {

                user.setLastLogin(now());
                userRepository.update(user);

                return ResponseEntity.ok(new LoginResponseDTO(
                        user.getEmail(),
                        UserRole.valueOf(user.getRole()),
                        jwtService.createJwt(user.getEmail())
                ));
            } else {
                log.warn("password did not match for: {}", request.email());
            }
        } else {
            log.warn("no user found with email: {}", request.email());
        }

        return ResponseEntity.status(UNAUTHORIZED).build();
    }

    @PostMapping("/register/helper")
    public ResponseEntity<String> registerHelper(@Valid @RequestBody RegisterHelperRequestDTO request) {
        log.info("POST /public/auth/register/helper: {}", request);

        var result = helperRegistrationService.register(request);
        log.info("result for request {}: {}", request, result);

        return switch (result) {
            case INVALID_EMAIL -> ResponseEntity.badRequest().build();
            case ALREADY_REGISTERED -> ResponseEntity.status(CONFLICT).build();
            case REGISTERED -> ResponseEntity.ok().build();
        };
    }

    @PostMapping("/register/newsletter")
    public ResponseEntity<String> registerNewsletter(@Valid @RequestBody RegisterNewsletterRequestDTO request) {
        log.info("POST /public/auth/register/newsletter: {}", request);

        var result = newsletterService.register(request);
        log.info("result for request {}: {}", request, result);

        return switch (result) {
            case INVALID_EMAIL -> ResponseEntity.badRequest().build();
            case ALREADY_REGISTERED -> ResponseEntity.status(CONFLICT).build();
            case REGISTERED -> ResponseEntity.ok().build();
        };
    }
}
