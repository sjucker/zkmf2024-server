CREATE TABLE location
(
    id        BIGINT          NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255)    NOT NULL,
    address   VARCHAR(255),
    latitude  DECIMAL(16, 12) NOT NULL,
    longitude DECIMAL(16, 12) NOT NULL,

    CONSTRAINT pk_location PRIMARY KEY (id)
);
