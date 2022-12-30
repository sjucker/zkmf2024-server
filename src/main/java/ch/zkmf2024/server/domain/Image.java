package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    // depending on type references different entities
    @Column(nullable = false)
    private Long foreignKey;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] content;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @Enumerated(STRING)
    @Column(nullable = false)
    private ImageType type;

    public enum ImageType {
        VEREIN_LOGO,
        VEREIN_BILD
    }

}
