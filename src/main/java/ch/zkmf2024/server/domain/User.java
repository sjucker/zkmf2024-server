package ch.zkmf2024.server.domain;

import ch.zkmf2024.server.dto.UserRole;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString(exclude = {"password"})
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime lastLogin;

    @Enumerated(STRING)
    @Column(nullable = false, name = "role")
    private UserRole userRole;

    private LocalDateTime createdAt;

    private String emailVerification;

    private LocalDateTime emailVerifiedAt;

}
