package ch.zkmf2024.server.security;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
public class JwtService {

    public static final String IMPERSONATE_CLAIM = "impersonate";

    private final SignatureAlgorithm signatureAlgorithm;
    private final SecretKeySpec secretKey;

    public JwtService(ApplicationProperties properties) {
        this.signatureAlgorithm = SignatureAlgorithm.forName(properties.getJwtSignatureAlgorithm());
        this.secretKey = new SecretKeySpec(properties.getJwtSecret().getBytes(UTF_8), signatureAlgorithm.getJcaName());
    }

    public String createJwt(String email, boolean impersonate) {
        var claims = Jwts.claims().setSubject(email);
        claims.put(IMPERSONATE_CLAIM, impersonate);

        return Jwts.builder()
                   .setClaims(claims)
                   // 100 days
                   .setExpiration(Date.from(Instant.now().plusSeconds(60 * 60 * 24 * 100L)))
                   .signWith(secretKey, signatureAlgorithm)
                   .compact();
    }

    public Optional<Claims> validateJwt(String jwt) {
        var jwtParser = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build();

        try {
            return Optional.of(jwtParser.parseClaimsJws(jwt).getBody());
        } catch (ExpiredJwtException e) {
            log.info("JWT was expired");
            return Optional.empty();
        } catch (JwtException ex) {
            log.error("unable to parse given JWT: " + jwt, ex);
            return Optional.empty();
        }
    }

}
