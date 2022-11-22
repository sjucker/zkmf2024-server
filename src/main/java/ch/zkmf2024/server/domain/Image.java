package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

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
