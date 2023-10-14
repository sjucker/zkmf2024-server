package ch.zkmf2024.server.security;

import ch.zkmf2024.server.configuration.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        var properties = new ApplicationProperties();
        properties.setJwtSecret("testBrU,,V12IS/NPRX><9<]Lf=oVBqm@z");
        jwtService = new JwtService(properties);
    }

    @Test
    void test() {
        var mail = "test@test.com";
        var jwt = jwtService.createJwt(mail);
        assertThat(jwtService.validateJwt(jwt)).hasValueSatisfying(claims -> {
            assertThat(claims.getSubject()).isEqualTo(mail);
            assertThat(claims.getExpiration()).isAfter(Instant.now());
        });
    }

    @Test
    void validateJwt() {
        var jwt = jwtService.createJwt("test@test.com");
        assertThat(jwtService.validateJwt("!" + jwt)).isEmpty();
    }
}
