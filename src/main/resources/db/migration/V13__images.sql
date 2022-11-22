CREATE TABLE image
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    foreign_key BIGINT                NOT NULL,
    content     LONGBLOB              NULL,
    name        VARCHAR(255)          NOT NULL,
    uploaded_at datetime              NOT NULL,
    type        VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);
