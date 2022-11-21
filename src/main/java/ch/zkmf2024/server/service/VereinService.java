package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.domain.Verein;
import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VerifyEmailRequestDTO;
import ch.zkmf2024.server.mapper.DTOMapper;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static ch.zkmf2024.server.dto.UserRole.VEREIN;

@Slf4j
@Service
public class VereinService {
    private static final DTOMapper MAPPER = DTOMapper.INSTANCE;

    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public VereinService(UserRepository userRepository,
                         VereinRepository vereinRepository,
                         PasswordEncoder passwordEncoder,
                         MailService mailService) {
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public Optional<VereinDTO> find(String email) {
        return vereinRepository.findByEmail(email)
                               .map(MAPPER::toDTO);
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
        user.setCreatedAt(LocalDateTime.now());
        user.setEmailVerification(UUID.randomUUID().toString());
        userRepository.save(user);

        var verein = new Verein();
        verein.setEmail(request.email());
        verein.getAngaben().setVereinsname(request.vereinsname());
        vereinRepository.save(verein);

        mailService.sendRegistrationEmail(user);
    }

    @Transactional
    public VereinDTO update(String email, VereinDTO dto) {
        // TODO verify user is allowed to update
        var verein = vereinRepository.findByEmail(email).orElseThrow();
        MAPPER.update(verein, dto);

        verein = vereinRepository.save(verein);
        return MAPPER.toDTO(verein);
    }

    @Transactional
    public boolean verifyEmail(VerifyEmailRequestDTO request) {
        var user = userRepository.findById(request.email()).orElseThrow();
        if (StringUtils.equals(user.getEmailVerification(), request.verification())) {
            user.setEmailVerification(null);
            user.setEmailVerifiedAt(LocalDateTime.now());
            userRepository.save(user);
            return true;
        } else {
            // TODO logging?
            return false;
        }
    }
}
