ALTER TABLE verein_programm_titel
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (fk_programm, fk_titel);

ALTER TABLE verein_programm_titel
    ADD CONSTRAINT uq_verein_programm_titel UNIQUE (fk_programm, fk_titel, position);
