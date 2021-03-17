package ch.zkmf2024.server.rest;

import ch.zkmf2024.server.dto.RegisterRequestDto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    @GetMapping
    public void login(String email, String password) {

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto request) {
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
        createRequest.setEmail(request.getEmail());
        // createRequest.setPassword("alskdjaslkd jaksldjaslkd j");
        try {
            FirebaseAuth instance = FirebaseAuth.getInstance();
            UserRecord user = instance.createUser(createRequest);

            return ResponseEntity.ok(user.getDisplayName());

        } catch (FirebaseAuthException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
