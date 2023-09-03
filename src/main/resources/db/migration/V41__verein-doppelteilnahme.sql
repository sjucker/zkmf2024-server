CREATE TABLE verein_doppeleinsatz
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    fk_verein       BIGINT       NOT NULL,
    fk_other_verein BIGINT       NOT NULL,
    name            VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE,
    FOREIGN KEY (fk_other_verein) REFERENCES verein (id) ON DELETE CASCADE
);
