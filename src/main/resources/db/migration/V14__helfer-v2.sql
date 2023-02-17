ALTER TABLE helper_registration
    DROP COLUMN available_friday;
ALTER TABLE helper_registration
    DROP COLUMN available_saturday;
ALTER TABLE helper_registration
    DROP COLUMN available_sunday;

ALTER TABLE helper_registration
    MODIFY COLUMN vorname varchar(255) NOT NULL;
ALTER TABLE helper_registration
    MODIFY COLUMN mobile varchar(255) NOT NULL;
ALTER TABLE helper_registration RENAME COLUMN mobile TO telefon;

ALTER TABLE helper_registration
    ADD UNIQUE (email);

ALTER TABLE helper_registration
    ADD COLUMN adresse varchar(255) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN plz_ort varchar(255) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN geburtsdatum date NOT NULL;

ALTER TABLE helper_registration
    ADD COLUMN vereinszugehoerigkeit varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN aufgaben varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN anzahl_einsaetze varchar(1024) NOT NULL;

ALTER TABLE helper_registration
    ADD COLUMN einsatz_mittwoch varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_donnerstag varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_freitag varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_samstag varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_sonntag varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_montag varchar(1024) NOT NULL;
ALTER TABLE helper_registration
    ADD COLUMN einsatz_dienstag varchar(1024) NOT NULL;

ALTER TABLE helper_registration
    ADD COLUMN groesse_shirt varchar(24) NOT NULL;

ALTER TABLE helper_registration
    ADD COLUMN registered_at datetime NOT NULL;

