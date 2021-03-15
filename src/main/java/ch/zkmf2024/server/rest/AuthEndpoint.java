package ch.zkmf2024.server.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/auth")
public class AuthEndpoint {

    @GetMapping
    public void login(String email, String password) {

    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest();
        request.setEmail("stefan.jucker@gmail.com");
        request.setPassword("alskdjaslkd jaksldjaslkd j");
        try {
            UserRecord user = FirebaseAuth.getInstance().createUser(request);

            return ResponseEntity.ok(user.getDisplayName());

        } catch (FirebaseAuthException e) {

            return ResponseEntity.badRequest().body(e.getAuthErrorCode().name());
        }
    }
}
