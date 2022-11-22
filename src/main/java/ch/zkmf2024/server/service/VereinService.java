package ch.zkmf2024.server.service;

import ch.zkmf2024.server.domain.Image;
import ch.zkmf2024.server.domain.Image.ImageType;
import ch.zkmf2024.server.domain.User;
import ch.zkmf2024.server.domain.Verein;
import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VerifyEmailRequestDTO;
import ch.zkmf2024.server.mapper.DTOMapper;
import ch.zkmf2024.server.repository.ImageRepository;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static ch.zkmf2024.server.domain.Image.ImageType.VEREIN_BILD;
import static ch.zkmf2024.server.domain.Image.ImageType.VEREIN_LOGO;
import static ch.zkmf2024.server.dto.UserRole.VEREIN;

@Slf4j
@Service
public class VereinService {
    private static final DTOMapper MAPPER = DTOMapper.INSTANCE;

    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public VereinService(UserRepository userRepository,
                         VereinRepository vereinRepository,
                         ImageRepository imageRepository,
                         PasswordEncoder passwordEncoder,
                         MailService mailService) {
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public Optional<VereinDTO> find(String email) {
        return vereinRepository.findByEmail(email)
                               .map(this::toDTO);
    }

    private VereinDTO toDTO(Verein verein) {
        var dto = MAPPER.toDTO(verein);
        imageRepository.findImageByForeignKeyAndType(verein.getId(), VEREIN_LOGO)
                       .ifPresent(image -> dto.setLogoImgId(image.getId()));

        imageRepository.findImageByForeignKeyAndType(verein.getId(), VEREIN_BILD)
                       .ifPresent(image -> dto.setBildImgId(image.getId()));

        return dto;
    }

    @Transactional
    public void create(RegisterVereinRequestDTO request) {
        if (userRepository.existsById(request.email())) {
            log.warn("tried to register new verein with already registerd email {}", request.email());
            throw new IllegalArgumentException("there is already a user with email: " + request.email());
        }

        if (vereinRepository.findByEmail(request.email()).isPresent()) {
            log.error("there is already a verein associated with email '{}' but no corresponding user found", request.email());
            throw new IllegalStateException();
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

        return toDTO(verein);
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
            log.error("could not verify email: {}", request);
            return false;
        }
    }

    @Transactional
    public void updateBilder(String email, MultipartFile logo, MultipartFile bild) throws IOException {
        var verein = vereinRepository.findByEmail(email).orElseThrow();
        saveImage(logo, verein.getId(), VEREIN_LOGO);
        saveImage(bild, verein.getId(), VEREIN_BILD);
    }

    private void saveImage(MultipartFile file, Long vereinId, ImageType imageType) throws IOException {
        if (file != null) {
            var image = imageRepository.findImageByForeignKeyAndType(vereinId, imageType)
                                       .orElseGet(() -> Image.builder().foreignKey(vereinId).type(imageType).build());
            image.setName(file.getOriginalFilename());
            image.setContent(file.getBytes());
            image.setUploadedAt(LocalDateTime.now());

            imageRepository.save(image);
        }
    }
}
