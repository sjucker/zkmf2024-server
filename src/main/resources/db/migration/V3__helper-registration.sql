CREATE TABLE helper_registration
(
    id                 BIGINT        NOT NULL AUTO_INCREMENT,
    name               VARCHAR(255)  NOT NULL,
    email              VARCHAR(255)  NOT NULL,
    available_friday   BIT(1)        NOT NULL,
    available_saturday BIT(1)        NOT NULL,
    available_sunday   BIT(1)        NOT NULL,
    comment            VARCHAR(1024) NULL,
    CONSTRAINT pk_helperregistration PRIMARY KEY (id)
);
