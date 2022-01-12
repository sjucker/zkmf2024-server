package ch.zkmf2024.server.configuration;

import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.repository.UserRepository;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.builder;

@Deprecated
@Service
public class FirebaseTokenUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserRepository userRepository;

    public FirebaseTokenUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        var email = (String) token.getPrincipal();
        return userRepository.findById(email)
                             .map(this::toUserDetails)
                             .orElseThrow(() -> new UsernameNotFoundException("unknown email: " + email));
    }

    private UserDetails toUserDetails(User user) {
        // build it like this so we do not have to implement the role-mapping ourselves
        return builder()
                .username(user.getEmail())
                .password("")
                .roles(user.getUserRole().toString())
                .build();
    }
}
