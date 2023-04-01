package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.ImageType;
import ch.zkmf2024.server.dto.RegisterVereinRequestDTO;
import ch.zkmf2024.server.dto.VereinDTO;
import ch.zkmf2024.server.dto.VereinsinfoDTO;
import ch.zkmf2024.server.dto.VerifyEmailRequestDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.ImagePojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.KontaktPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.VereinStatusPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.Zkmf2024UserPojo;
import ch.zkmf2024.server.mapper.VereinMapper;
import ch.zkmf2024.server.repository.ImageRepository;
import ch.zkmf2024.server.repository.UserRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static ch.zkmf2024.server.dto.ImageType.VEREIN_BILD;
import static ch.zkmf2024.server.dto.ImageType.VEREIN_LOGO;
import static ch.zkmf2024.server.dto.UserRole.VEREIN;
import static ch.zkmf2024.server.service.DateUtil.now;

@Slf4j
@Service
public class VereinService {

    private static final VereinMapper MAPPER = VereinMapper.INSTANCE;

    private final UserRepository userRepository;
    private final VereinRepository vereinRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final DSLContext jooqDsl;

    public VereinService(UserRepository userRepository,
                         VereinRepository vereinRepository,
                         ImageRepository imageRepository,
                         PasswordEncoder passwordEncoder,
                         MailService mailService,
                         DSLContext jooqDsl) {
        this.userRepository = userRepository;
        this.vereinRepository = vereinRepository;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.jooqDsl = jooqDsl;
    }

    public Optional<VereinDTO> find(String email) {
        return vereinRepository.findByEmail(email)
                               .map(this::toDTO);
    }

    public List<VereinDTO> findAll() {
        return vereinRepository.findAll().stream()
                               .map(this::toDTO)
                               .toList();
    }

    private VereinDTO toDTO(VereinPojo verein) {
        var praesident = vereinRepository.findKontaktById(verein.getPraesidentKontaktId());
        var direktion = vereinRepository.findKontaktById(verein.getDirektionKontaktId());

        var logoImgId = imageRepository.findImageByForeignKeyAndType(verein.getId(), VEREIN_LOGO)
                                       .map(ImagePojo::getId)
                                       .orElse(null);

        var bildImgId = imageRepository.findImageByForeignKeyAndType(verein.getId(), VEREIN_BILD)
                                       .map(ImagePojo::getId)
                                       .orElse(null);

        return new VereinDTO(
                verein.getEmail(),
                MAPPER.toVereinsangabenDTO(verein),
                MAPPER.toKontaktDTO(praesident),
                MAPPER.toKontaktDTO(direktion),
                MAPPER.toVereinsanmeldungDTO(verein),
                new VereinsinfoDTO(logoImgId, bildImgId, verein.getWebsiteText())
        );
    }

    public void create(RegisterVereinRequestDTO request) {
        if (userRepository.existsById(request.email())) {
            log.warn("tried to register new verein with already registerd email {}", request.email());
            throw new IllegalArgumentException("there is already a user with email: " + request.email());
        }

        if (vereinRepository.findByEmail(request.email()).isPresent()) {
            log.error("there is already a verein associated with email '{}' but no corresponding user found", request.email());
            throw new IllegalStateException();
        }

        jooqDsl.transaction(configuration -> {
            var user = new Zkmf2024UserPojo();
            user.setEmail(request.email());
            user.setPassword(passwordEncoder.encode(request.password()));
            user.setRole(VEREIN.name());
            user.setCreatedAt(now());
            user.setEmailVerification(UUID.randomUUID().toString());
            userRepository.insert(user);

            var kontaktPraesident = new KontaktPojo();
            vereinRepository.insert(kontaktPraesident);
            var kontaktDirektion = new KontaktPojo();
            vereinRepository.insert(kontaktDirektion);

            var verein = new VereinPojo();
            verein.setEmail(request.email());
            verein.setVereinsname(request.vereinsname());
            verein.setPraesidentKontaktId(kontaktPraesident.getId());
            verein.setDirektionKontaktId(kontaktDirektion.getId());

            vereinRepository.insert(verein);

            var status = new VereinStatusPojo();
            status.setFkVerein(verein.getId());
            vereinRepository.insert(status);

            mailService.sendRegistrationEmail(user);
        });

    }

    public VereinDTO update(String email, VereinDTO dto) {
        // TODO verify user is allowed to update
        var verein = vereinRepository.findByEmail(email).orElseThrow();

        MAPPER.updateVereinsangaben(verein, dto.angaben());
        MAPPER.updateVereinsanmeldung(verein, dto.anmeldung());
        verein.setWebsiteText(dto.info().websiteText());
        vereinRepository.update(verein);

        var praesident = vereinRepository.findKontaktById(verein.getPraesidentKontaktId());
        MAPPER.updateKontakt(praesident, dto.praesident());
        vereinRepository.update(praesident);

        var direktion = vereinRepository.findKontaktById(verein.getDirektionKontaktId());
        MAPPER.updateKontakt(direktion, dto.direktion());
        vereinRepository.update(direktion);

        dto = toDTO(verein);

        var status = vereinRepository.findStatusById(verein.getId());
        status.setPhase1(dto.getPhase1Status().name());
        vereinRepository.update(status);

        return dto;
    }

    public boolean verifyEmail(VerifyEmailRequestDTO request) {
        var user = userRepository.findById(request.email()).orElseThrow();
        if (StringUtils.equals(user.getEmailVerification(), request.verification())) {
            user.setEmailVerification(null);
            user.setEmailVerifiedAt(now());
            userRepository.update(user);
            return true;
        } else {
            log.error("could not verify email: {}", request);
            return false;
        }
    }

    public void updateBilder(String email, MultipartFile logo, MultipartFile bild) throws IOException {
        var verein = vereinRepository.findByEmail(email).orElseThrow();
        saveImage(logo, verein.getId(), VEREIN_LOGO);
        saveImage(bild, verein.getId(), VEREIN_BILD);
    }

    private void saveImage(MultipartFile file, Long vereinId, ImageType imageType) throws IOException {
        if (file != null) {
            var image = imageRepository.findImageByForeignKeyAndType(vereinId, imageType);
            if (image.isPresent()) {
                var pojo = image.get();
                pojo.setName(file.getOriginalFilename());
                pojo.setContent(file.getBytes());
                pojo.setUploadedAt(now());
                imageRepository.update(pojo);
            } else {
                var pojo = new ImagePojo();
                pojo.setForeignKey(vereinId);
                pojo.setType(imageType.name());
                pojo.setName(file.getOriginalFilename());
                pojo.setContent(file.getBytes());
                pojo.setUploadedAt(now());
                imageRepository.insert(pojo);
            }
        }
    }

    public void forgotPassword(String email) {
        userRepository.findByIdAndRole(email, VEREIN).ifPresentOrElse(
                user -> {
                    user.setPasswordResetToken(UUID.randomUUID().toString());
                    userRepository.update(user);

                    mailService.sendResetPasswordEmail(user);
                },
                () -> log.warn("forgot password called for unknown user: {}", email)
        );
    }

    public boolean resetPassword(String email, String token, String newPassword) {
        var user = userRepository.findByIdAndRole(email, VEREIN);
        if (user.isPresent()) {
            var userPojo = user.get();
            if (StringUtils.equals(userPojo.getPasswordResetToken(), token)) {
                userPojo.setPassword(passwordEncoder.encode(newPassword));
                userPojo.setPasswordResetToken(null);
                userRepository.update(userPojo);

                log.info("updated password for user {}", email);
                return true;
            } else {
                log.warn("presented password reset-token did not match expected one: {}/{}",
                        token,
                        userPojo.getPasswordResetToken());
            }
        } else {
            log.warn("tried to reset password for unknown user: {}, {}", email, token);
        }
        return false;
    }

    public void deleteImage(String email, Long id) {
        var image = imageRepository.findById(id).orElseThrow();
        var verein = vereinRepository.findByEmail(email).orElseThrow();

        if (Objects.equals(verein.getId(), image.getForeignKey())) {
            imageRepository.delete(image);
        } else {
            log.error("user with email '{}' tried to delete image with id {}, but does not belong to them!", email, id);
        }
    }
}
