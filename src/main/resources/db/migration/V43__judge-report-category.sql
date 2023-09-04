CREATE TABLE judge_report_comment
(
    fk_judge_report BIGINT NOT NULL,
    fk_titel        BIGINT NOT NULL,
    comment         VARCHAR(4096),
    CONSTRAINT judge_report_comment PRIMARY KEY (fk_judge_report, fk_titel),
    CONSTRAINT judge_report_comment_report FOREIGN KEY (fk_judge_report) REFERENCES judge_report (id),
    CONSTRAINT judge_report_comment_titel FOREIGN KEY (fk_titel) REFERENCES titel (id)
);


CREATE TABLE judge_report_rating
(
    fk_judge_report BIGINT       NOT NULL,
    fk_titel        BIGINT       NOT NULL,
    category        VARCHAR(255) NOT NULL,
    rating          VARCHAR(255) NOT NULL,
    comment         VARCHAR(1024),
    CONSTRAINT judge_report_rating PRIMARY KEY (fk_judge_report, fk_titel, category),
    CONSTRAINT judge_report_rating_report FOREIGN KEY (fk_judge_report) REFERENCES judge_report (id),
    CONSTRAINT judge_report_rating_titel FOREIGN KEY (fk_titel) REFERENCES titel (id)
);
