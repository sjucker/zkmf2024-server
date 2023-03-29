ALTER TABLE verein
    ADD COLUMN direktion_doppeleinsatz BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE verein
    ADD COLUMN direktion_doppeleinsatz_verein VARCHAR(255);
ALTER TABLE verein
    ADD COLUMN mitspieler_doppeleinsatz BIT(1) NOT NULL DEFAULT 0;
