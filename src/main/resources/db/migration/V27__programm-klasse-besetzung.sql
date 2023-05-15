ALTER TABLE verein_programm
    ADD COLUMN klasse VARCHAR(255) NULL AFTER modul;

ALTER TABLE verein_programm
    ADD COLUMN besetzung VARCHAR(255) NULL AFTER klasse;
