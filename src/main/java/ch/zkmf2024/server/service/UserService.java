package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.admin.UserCreateDTO;
import ch.zkmf2024.server.dto.admin.UserDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.toRootLowerCase;

@Slf4j
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDTO create(UserCreateDTO dto) {
        if (userRepository.existsById(dto.email())) {
            throw new IllegalArgumentException("%s email already used for other user".formatted(dto.email()));
        }

        userRepository.insert(new Zkmf2024UserPojo(
                toRootLowerCase(dto.email()),
                dto.role().name(),
                passwordEncoder.encode(dto.password()),
                null,
                DateUtil.now(),
                UUID.randomUUID().toString(),
                null,
                null,
                false
        ));

        return new UserDTO(dto.email(), dto.role(), null);
    }
}
