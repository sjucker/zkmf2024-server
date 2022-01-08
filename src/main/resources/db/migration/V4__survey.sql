CREATE TABLE survey_answer
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    timestamp              datetime              NOT NULL,
    vereins_name           VARCHAR(255)          NOT NULL,
    besetzung              VARCHAR(255)          NOT NULL,
    staerke_klasse         VARCHAR(255)          NOT NULL,
    anzahl_mitglieder      VARCHAR(255)          NOT NULL,
    kontakt_name           VARCHAR(255)          NOT NULL,
    kontakt_email          VARCHAR(255)          NOT NULL,
    kontakt_telefon        VARCHAR(255)          NOT NULL,
    modul_auswahl          VARCHAR(255)          NULL,
    absage_kommentar       VARCHAR(1024)         NULL,
    absage_kontaktaufnahme VARCHAR(255)          NULL,
    helfer                 VARCHAR(255)          NULL,
    CONSTRAINT pk_survey_answer PRIMARY KEY (id)
);
