package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.dto.RegisterHelperRequestDto;
import ch.zkmf2024.server.dto.RegisterRequestDto;
import ch.zkmf2024.server.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ch.zkmf2024.server.domain.User.UserRole.BAND;

@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEndpoint.class);

    private final UserRepository userRepository;

    public AuthEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register/band")
    public ResponseEntity<String> registerBand(@RequestBody RegisterRequestDto request) {
        logger.info("/public/auth/register: {}", request);

        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(request.email());
        createRequest.setPassword(request.password());

        try {
            FirebaseAuth instance = FirebaseAuth.getInstance();
            UserRecord user = instance.createUser(createRequest);

            userRepository.save(new User(user.getEmail(), BAND));

            // TODO send email verification mail
            // TODO persist contact info

            logger.info("user {} successfully created", user.getEmail());

            return ResponseEntity.ok().build();

        } catch (FirebaseAuthException e) {
            logger.error("/public/auth/register", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/helper")
    public ResponseEntity<String> registerHelper(@RequestBody RegisterHelperRequestDto request) {
        logger.error("request={}", request);
        return ResponseEntity.ok().build();
    }
}
