package ch.zkmf2024.server.domain;

import ch.zkmf2024.server.dto.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
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

}
