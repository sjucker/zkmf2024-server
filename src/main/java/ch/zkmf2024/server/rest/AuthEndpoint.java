package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.RegisterRequestDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEndpoint.class);

    @GetMapping
    public ResponseEntity<String> login(String email, String password) {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request) {
        logger.info("/public/auth/register: {}", request);
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(request.email());
        // createRequest.setPassword("alskdjaslkd jaksldjaslkd j");
        try {
            FirebaseAuth instance = FirebaseAuth.getInstance();
            UserRecord user = instance.createUser(createRequest);

            return ResponseEntity.ok(user.getDisplayName());

        } catch (FirebaseAuthException e) {
            logger.error("/public/auth/register", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
