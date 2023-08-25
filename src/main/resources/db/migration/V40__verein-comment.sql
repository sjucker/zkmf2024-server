CREATE TABLE verein_comment
(
    id         BIGINT        NOT NULL AUTO_INCREMENT,
    fk_verein  BIGINT        NULL,
    comment    VARCHAR(8000) NOT NULL,
    created_at DATETIME      NOT NULL,
    created_by VARCHAR(255)  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_verein) REFERENCES verein (id) ON DELETE CASCADE
);
