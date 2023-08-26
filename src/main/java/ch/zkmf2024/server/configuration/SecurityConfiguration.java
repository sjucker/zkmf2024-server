package ch.zkmf2024.server.configuration;

import ch.zkmf2024.server.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addExposedHeader(AUTHORIZATION);
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.httpBasic(AbstractHttpConfigurer::disable)
                   .cors(withDefaults())
                   .csrf(AbstractHttpConfigurer::disable)
                   .formLogin(AbstractHttpConfigurer::disable)
                   .logout(AbstractHttpConfigurer::disable)
                   .authorizeHttpRequests(registry -> {
                       registry.requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll();
                       registry.requestMatchers(new AntPathRequestMatcher("/secured/**")).authenticated();
                       registry.anyRequest().permitAll();
                   })
                   .exceptionHandling(withDefaults())
                   .sessionManagement(configurer -> configurer.sessionCreationPolicy(STATELESS))
                   .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
    }

}
