CREATE TABLE judge
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    name  VARCHAR(255) NOT NULL,

    CONSTRAINT pk_judge PRIMARY KEY (id),
    CONSTRAINT uq_judge_email UNIQUE (email)
);
