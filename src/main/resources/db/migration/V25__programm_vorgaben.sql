CREATE TABLE programm_vorgaben
(
    modul                   VARCHAR(255) NOT NULL,
    klasse                  VARCHAR(255) NOT NULL,
    besetzung               VARCHAR(255) NOT NULL,
    min_duration_in_seconds INT          NOT NULL,
    max_duration_in_seconds INT          NOT NULL,
    PRIMARY KEY (modul, klasse, besetzung)
);

INSERT INTO programm_vorgaben (modul, klasse, besetzung, min_duration_in_seconds, max_duration_in_seconds)
VALUES ('A', 'HOECHSTKLASSE', 'HARMONIE', 50 * 60, 60 * 60),
       ('A', 'KLASSE_1', 'HARMONIE', 35 * 60, 45 * 60),
       ('A', 'KLASSE_2', 'HARMONIE', 25 * 60, 30 * 60),
       ('A', 'KLASSE_3', 'HARMONIE', 20 * 60, 25 * 60),
       ('A', 'KLASSE_4', 'HARMONIE', 18 * 60, 22 * 60),

       ('A', 'HOECHSTKLASSE', 'BRASS_BAND', 50 * 60, 60 * 60),
       ('A', 'KLASSE_1', 'BRASS_BAND', 35 * 60, 45 * 60),
       ('A', 'KLASSE_2', 'BRASS_BAND', 25 * 60, 30 * 60),
       ('A', 'KLASSE_3', 'BRASS_BAND', 20 * 60, 25 * 60),
       ('A', 'KLASSE_4', 'BRASS_BAND', 18 * 60, 22 * 60),

       ('B', 'OBERSTUFE', 'HARMONIE', 20 * 60, 25 * 60),
       ('B', 'MITTELSTUFE', 'HARMONIE', 15 * 60, 20 * 60),
       ('B', 'UNTERSTUFE', 'HARMONIE', 10 * 60, 15 * 60),

       ('B', 'OBERSTUFE', 'BRASS_BAND', 20 * 60, 25 * 60),
       ('B', 'MITTELSTUFE', 'BRASS_BAND', 15 * 60, 20 * 60),
       ('B', 'UNTERSTUFE', 'BRASS_BAND', 10 * 60, 15 * 60),

       ('E', 'UNTERSTUFE', 'BRASS_BAND', 10 * 60, 15 * 60),

       ('H', 'OBERSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60),
       ('H', 'MITTELSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60),
       ('H', 'UNTERSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60);
