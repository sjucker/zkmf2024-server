package ch.zkmf2024.server.security;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static ch.zkmf2024.server.dto.UserRole.IMPERSONATE;
import static ch.zkmf2024.server.security.JwtService.IMPERSONATE_CLAIM;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserAuthService userAuthService;

    public JwtRequestFilter(JwtService jwtService, UserAuthService userAuthService) {
        this.jwtService = jwtService;
        this.userAuthService = userAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
            var jwtToken = authorizationHeader.substring(7);
            Optional<Claims> jwt = jwtService.validateJwt(jwtToken);
            if (jwt.isPresent()) {
                var email = jwt.map(Claims::getSubject).orElseThrow();
                var impersonate = jwt.map(claims -> claims.get(IMPERSONATE_CLAIM, Boolean.class)).orElse(false);
                var userDetails = userAuthService.loadUserByUsername(email);

                if (userDetails != null) {
                    var authorities = new ArrayList<GrantedAuthority>(userDetails.getAuthorities());
                    if (impersonate) {
                        authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority(IMPERSONATE.name()));
                    }
                    var authentication = new UsernamePasswordAuthenticationToken(new User(userDetails.getUsername(), userDetails.getPassword(), authorities), null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
