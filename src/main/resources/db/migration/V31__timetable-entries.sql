CREATE TABLE timetable_entry
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    fk_verein   BIGINT       NOT NULL,
    modul       VARCHAR(255) NOT NULL,
    klasse      VARCHAR(255) NULL,
    besetzung   VARCHAR(255) NULL,
    fk_location BIGINT       NOT NULL,
    date        DATE         NOT NULL,
    start_time  TIME         NOT NULL,
    end_time    TIME         NOT NULL,

    CONSTRAINT pk_timetable PRIMARY KEY (id),
    CONSTRAINT uq_timetable UNIQUE (fk_verein, modul),
    CONSTRAINT fk_timetable_verein FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE,
    CONSTRAINT fk_timetable_location FOREIGN KEY (fk_location) REFERENCES location (id) ON DELETE CASCADE
);
