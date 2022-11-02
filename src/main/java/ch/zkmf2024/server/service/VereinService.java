package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.domain.Verein;
import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.mapper.DTOMapper;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static ch.zkmf2024.server.dto.UserRole.VEREIN;

@Slf4j
@Service
public class VereinService {
    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final PasswordEncoder passwordEncoder;

    public VereinService(UserRepository userRepository,
                         VereinRepository vereinRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<VereinDTO> find(String email) {
        return vereinRepository.findByEmail(email)
                               .map(DTOMapper.INSTANCE::toDTO);
    }

    @Transactional
    public void create(RegisterVereinRequestDTO request) {
        if (userRepository.existsById(request.email())) {
            log.warn("tried to register new verein with already registerd email {}", request.email());
            throw new IllegalArgumentException("there is already a user with email: " + request.email());
        }

        var user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setUserRole(VEREIN);
        userRepository.save(user);

        var verein = new Verein();
        verein.setEmail(request.email());
        verein.getAngaben().setVereinsname(request.vereinsname());
        vereinRepository.save(verein);
    }
}
