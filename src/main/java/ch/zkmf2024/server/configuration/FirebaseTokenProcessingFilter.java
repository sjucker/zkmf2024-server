package ch.zkmf2024.server.configuration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.servlet.http.HttpServletRequest;

public class FirebaseTokenProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        var header = httpServletRequest.getHeader("Authorization");

        if (header == null || !header.startsWith(BEARER_TOKEN_PREFIX)) {
            throw new PreAuthenticatedCredentialsNotFoundException("No JWT token found in request headers");
        }

        try {
            // strip away the leading "Bearer "
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(header.replace(BEARER_TOKEN_PREFIX, ""));
            return firebaseToken.getEmail();
        } catch (FirebaseAuthException e) {
            throw new PreAuthenticatedCredentialsNotFoundException("firebase token could not be verified", e);
        }
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return ""; // not needed
    }
}
