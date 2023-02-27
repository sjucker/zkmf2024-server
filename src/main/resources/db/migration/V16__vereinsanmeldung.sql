ALTER TABLE verein
    ADD COLUMN modula BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN modulb BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN modulc BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN moduld BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN module BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN modulf BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN modulg BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN modulh BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN klasse_modula VARCHAR(255) NULL;
ALTER TABLE verein
    ADD COLUMN klasse_modulb VARCHAR(255) NULL;
ALTER TABLE verein
    ADD COLUMN klasse_modulh VARCHAR(255) NULL;
ALTER TABLE verein
    ADD COLUMN harmonie BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN brass_band BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN fanfare BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN tambouren BIT(1) NOT NULL;
ALTER TABLE verein
    ADD COLUMN perkussionsensemble BIT(1) NOT NULL;
