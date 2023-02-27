ALTER TABLE verein
    DROP CONSTRAINT FK_VEREIN_ON_KASSIER_KONTAKT;

ALTER TABLE verein
    DROP COLUMN kassier_kontakt_id;

ALTER TABLE verein
    DROP COLUMN staerke_klasse;

ALTER TABLE verein
    DROP COLUMN anzahl_musikanten;

ALTER TABLE verein
    DROP COLUMN anzahl_dirigenten;

ALTER TABLE verein
    DROP COLUMN anzahl_tambouren;
