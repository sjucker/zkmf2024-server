package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "helper_registration")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HelperRegistration {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String mobile;

    @Column(nullable = false, name = "available_friday")
    private boolean availableFriday;

    @Column(nullable = false, name = "available_saturday")
    private boolean availableSaturday;

    @Column(nullable = false, name = "available_sunday")
    private boolean availableSunday;

    private String comment;

}
