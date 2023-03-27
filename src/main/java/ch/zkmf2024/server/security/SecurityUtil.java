package ch.zkmf2024.server.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static Optional<String> getUsername() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken token) {
            return Optional.ofNullable(((UserDetails) token.getPrincipal()).getUsername());
        }
        return Optional.empty();
    }
}
