ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_a_1 VARCHAR(255);
ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_a_2 VARCHAR(255);

ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_a_titel_1_id BIGINT;
ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_a_titel_2_id BIGINT;

ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_b_titel_id BIGINT;

ALTER TABLE verein_programm
    ADD COLUMN tambouren_kat_c_titel_id BIGINT;
