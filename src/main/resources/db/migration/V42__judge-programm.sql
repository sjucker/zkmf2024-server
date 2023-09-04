ALTER TABLE timetable_entry
    DROP COLUMN modul;
ALTER TABLE timetable_entry
    DROP COLUMN klasse;
ALTER TABLE timetable_entry
    DROP COLUMN besetzung;

ALTER TABLE timetable_entry
    ADD COLUMN fk_verein_programm BIGINT NOT NULL AFTER fk_verein;

ALTER TABLE timetable_entry
    ADD CONSTRAINT fk_timetable_programm FOREIGN KEY (fk_verein_programm) REFERENCES verein_programm (id) ON DELETE CASCADE;
