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
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
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
        web.ignoring()
           .antMatchers("/public/**")
           .antMatchers(OPTIONS);
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
            .logout().disable();
    }

    @Bean
    public FirebaseTokenProcessingFilter preAuthFilter() throws Exception {
        FirebaseTokenProcessingFilter firebaseTokenProcessingFilter = new FirebaseTokenProcessingFilter();
        firebaseTokenProcessingFilter.setAuthenticationManager(authenticationManager());
        firebaseTokenProcessingFilter.setRequiresAuthenticationRequestMatcher(new OrRequestMatcher(
                new AntPathRequestMatcher("/secured/**", GET.name()),
                new AntPathRequestMatcher("/secured/**", POST.name()),
                new AntPathRequestMatcher("/secured/**", PUT.name()),
                new AntPathRequestMatcher("/secured/**", PATCH.name()),
                new AntPathRequestMatcher("/secured/**", DELETE.name())
        ));
        return firebaseTokenProcessingFilter;
    }

    @Bean
    public AuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(service);
        return provider;
    }

}
