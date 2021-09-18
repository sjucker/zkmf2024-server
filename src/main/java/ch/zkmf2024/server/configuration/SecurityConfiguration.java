package ch.zkmf2024.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final FirebaseTokenUserDetailsService service;

    public SecurityConfiguration(FirebaseTokenUserDetailsService service) {
        this.service = service;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/secured/**")
            .addFilterBefore(preAuthFilter(), RequestHeaderAuthenticationFilter.class)
            .authenticationProvider(preAuthProvider())
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(STATELESS)
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // allow all Cross Origin Requests
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public FirebaseTokenProcessingFilter preAuthFilter() throws Exception {
        FirebaseTokenProcessingFilter firebaseTokenProcessingFilter = new FirebaseTokenProcessingFilter();
        firebaseTokenProcessingFilter.setAuthenticationManager(authenticationManager());
        firebaseTokenProcessingFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/secured/**"));
        return firebaseTokenProcessingFilter;
    }

    @Bean
    public AuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(service);
        return provider;
    }


}
