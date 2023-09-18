ALTER TABLE verein
    ADD COLUMN facebook VARCHAR(255) NULL AFTER homepage;

ALTER TABLE verein
    ADD COLUMN instagram VARCHAR(255) NULL AFTER facebook;
