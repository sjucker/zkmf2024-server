package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.dto.RegisterHelperRequestDTO;
import ch.zkmf2024.server.dto.RegisterNewsletterRequestDTO;
import ch.zkmf2024.server.dto.RegisterRequestDTO;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.service.NewsletterService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static ch.zkmf2024.server.domain.User.UserRole.BAND;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    private final UserRepository userRepository;
    private final NewsletterService newsletterService;

    public AuthEndpoint(UserRepository userRepository,
                        NewsletterService newsletterService) {
        this.userRepository = userRepository;
        this.newsletterService = newsletterService;
    }

    @PostMapping("/register/band")
    public ResponseEntity<String> registerBand(@RequestBody RegisterRequestDTO request) {
        log.info("POST /public/auth/register/band: {}", request);

        // TODO move this into service
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(request.email());
        createRequest.setPassword(request.password());

        try {
            FirebaseAuth instance = FirebaseAuth.getInstance();
            UserRecord user = instance.createUser(createRequest);

            userRepository.save(new User(user.getEmail(), BAND));

            // TODO send email verification mail
            // TODO persist contact info

            log.info("user {} successfully created", user.getEmail());

            return ResponseEntity.ok().build();

        } catch (FirebaseAuthException e) {
            log.error("/public/auth/register/band", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/helper")
    public ResponseEntity<String> registerHelper(@RequestBody RegisterHelperRequestDTO request) {
        // TODO write to DB
        log.info("POST /public/auth/register/helper: {}", request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register/newsletter")
    public ResponseEntity<String> registerNewsletter(@Valid @RequestBody RegisterNewsletterRequestDTO request) {
        log.info("POST /public/auth/register/newsletter: {}", request);

        var result = newsletterService.register(request);
        log.info("result for request {}: {}", request, result);

        return switch (result) {
            case INVALID_EMAIL -> ResponseEntity.badRequest().build();
            case ALREADY_REGISTERED -> ResponseEntity.status(CONFLICT).build();
            case CREATED -> ResponseEntity.ok().build();
        };
    }
}
