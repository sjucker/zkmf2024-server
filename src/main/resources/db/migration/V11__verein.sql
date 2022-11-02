DROP TABLE band;

CREATE TABLE kontakt
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    vorname        VARCHAR(255) NULL,
    nachname       VARCHAR(255) NULL,
    adresse        VARCHAR(255) NULL,
    plz            INT          NULL,
    ort            VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    telefon_privat VARCHAR(255) NULL,
    telefon_mobile VARCHAR(255) NULL,
    CONSTRAINT pk_kontakt PRIMARY KEY (id)
);

CREATE TABLE verein
(
    id                    BIGINT       NOT NULL AUTO_INCREMENT,
    email                 VARCHAR(255) NOT NULL,
    praesident_kontakt_id BIGINT       NOT NULL,
    kassier_kontakt_id    BIGINT       NOT NULL,
    direktion_kontakt_id  BIGINT       NOT NULL,
    staerke_klasse        VARCHAR(255) NULL,
    anzahl_musikanten     INT          NULL,
    anzahl_dirigenten     INT          NULL,
    anzahl_tambouren      INT          NULL,
    vereinsname           VARCHAR(255) NULL,
    adresse               VARCHAR(255) NULL,
    plz                   INT          NULL,
    ort                   VARCHAR(255) NULL,
    homepage              VARCHAR(255) NULL,
    iban                  VARCHAR(255) NULL,
    CONSTRAINT pk_verein PRIMARY KEY (id)
);

ALTER TABLE verein
    ADD CONSTRAINT FK_VEREIN_ON_DIREKTION_KONTAKT FOREIGN KEY (direktion_kontakt_id) REFERENCES kontakt (id);

ALTER TABLE verein
    ADD CONSTRAINT FK_VEREIN_ON_KASSIER_KONTAKT FOREIGN KEY (kassier_kontakt_id) REFERENCES kontakt (id);

ALTER TABLE verein
    ADD CONSTRAINT FK_VEREIN_ON_PRAESIDENT_KONTAKT FOREIGN KEY (praesident_kontakt_id) REFERENCES kontakt (id);
