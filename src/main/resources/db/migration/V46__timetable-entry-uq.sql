ALTER TABLE timetable_entry
    DROP FOREIGN KEY fk_timetable_verein;

ALTER TABLE timetable_entry
    DROP CONSTRAINT uq_timetable;

ALTER TABLE timetable_entry
    ADD CONSTRAINT fk_timetable_verein FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE;

ALTER TABLE timetable_entry
    ADD CONSTRAINT uq_timetable UNIQUE (fk_verein, fk_verein_programm);
