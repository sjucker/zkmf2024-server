package ch.zkmf2024.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "newsletter_recipient")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterRecipient {

    @Id
    private String email;

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "subscribed_at")
    private LocalDateTime subscribedAt;


    @Column(name = "unsubscribed_at")
    private LocalDateTime unsubscribedAt;

}
