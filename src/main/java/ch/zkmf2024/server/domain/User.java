package ch.zkmf2024.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import static javax.persistence.EnumType.STRING;

@Entity
public class User {

    @Id
    private String email;

    @Enumerated(STRING)
    @Column(name = "role")
    private UserRole userRole;


    public User() {
    }

    public User(String email, UserRole userRole) {
        this.email = email;
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public enum UserRole {
        BAND,
        HELPER,
        PLANER,
        ADMIN
    }

}
