CREATE TABLE titel
(
    id                  BIGINT        NOT NULL AUTO_INCREMENT,
    fk_verein           BIGINT        NULL,
    titel_name          VARCHAR(255)  NOT NULL,
    composer            VARCHAR(255)  NOT NULL,
    arrangeur           VARCHAR(255),
    grad                DECIMAL(2, 1) NULL,
    duration_in_seconds INT           NOT NULL,
    modul               VARCHAR(255)  NULL,
    klasse              VARCHAR(255)  NULL,
    besetzung           VARCHAR(255)  NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE
);

-- Pflichtstücke
INSERT INTO titel (fk_verein, titel_name, composer, duration_in_seconds, modul, klasse, besetzung)
VALUES (null, 'Traveler', 'David Maslanka', 796, 'A', 'HOECHSTKLASSE', 'HARMONIE'),
       (null, 'La Mezquita de Cordoba', 'Julie Giroux', 701, 'A', 'KLASSE_1', 'HARMONIE'),
       (null, 'QuaranTime', 'Stephan Hodel', 459, 'A', 'KLASSE_2', 'HARMONIE'),
       (null, 'May the Forest Bloom Again', 'Théo Schmitt', 398, 'A', 'KLASSE_3', 'HARMONIE'),
       (null, 'Samurai', 'Mathias Wehr', 345, 'A', 'KLASSE_4', 'HARMONIE'),

       (null, 'Metropolis 1927', 'Peter Graham', 950, 'A', 'HOECHSTKLASSE', 'BRASS_BAND'),
       (null, 'Circius', 'Torstein Aagard-Nilsen', 542, 'A', 'KLASSE_1', 'BRASS_BAND'),
       (null, 'Airs and Dances', 'Alan Fernie', 446, 'A', 'KLASSE_2', 'BRASS_BAND'),
       (null, 'The Pioneers', 'Philip Sparke', 345, 'A', 'KLASSE_3', 'BRASS_BAND'),
       (null, 'Samurai', 'Mathias Wehr', 345, 'A', 'KLASSE_4', 'BRASS_BAND'),

       (null, 'Halvdan Sivertsen Medley', 'Lars Erik Gudim', 390, 'B', 'OBERSTUFE', 'HARMONIE'),
       (null, 'We''ll Make It Work', 'Christoph Walter', 300, 'B', 'MITTELSTUFE', 'HARMONIE'),
       (null, 'On a Roll', 'Oliver Waespi', 250, 'B', 'UNTERSTUFE', 'HARMONIE'),

       (null, 'Windows of the World (1. Satz)', 'Peter Graham', 110, 'B', 'OBERSTUFE', 'BRASS_BAND'),
       (null, 'Windows of the World (3. Satz)', 'Peter Graham', 230, 'B', 'OBERSTUFE', 'BRASS_BAND'),
       (null, 'We''ll Make It Work', 'Christoph Walter', 300, 'B', 'MITTELSTUFE', 'BRASS_BAND'),
       (null, 'On a Roll', 'Oliver Waespi', 250, 'B', 'UNTERSTUFE', 'BRASS_BAND');

CREATE TABLE verein_programm
(
    id                        BIGINT       NOT NULL AUTO_INCREMENT,
    fk_verein                 BIGINT       NOT NULL,
    modul                     VARCHAR(255) NOT NULL,
    titel                     VARCHAR(255),
    info_moderation           VARCHAR(4096),
    total_duration_in_seconds INT,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE
);

CREATE TABLE verein_programm_titel
(
    fk_programm         BIGINT NOT NULL,
    fk_titel            BIGINT NOT NULL,
    position            INT    NOT NULL,
    duration_in_seconds INT,
    applaus_in_seconds  INT,
    PRIMARY KEY (fk_programm, fk_titel, position),
    FOREIGN KEY (fk_programm) REFERENCES verein_programm (id) ON DELETE CASCADE,
    FOREIGN KEY (fk_titel) REFERENCES titel (id) ON DELETE CASCADE
);
