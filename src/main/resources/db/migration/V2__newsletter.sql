CREATE TABLE newsletter_recipient
(
    email           VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    subscribed_at   datetime     NOT NULL,
    unsubscribed_at datetime     NULL,
    CONSTRAINT pk_newsletterrecipient PRIMARY KEY (email)
);
