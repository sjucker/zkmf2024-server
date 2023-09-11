ALTER TABLE judge_report_rating
    DROP CONSTRAINT judge_report_rating_report;
ALTER TABLE judge_report_rating
    DROP CONSTRAINT judge_report_rating_titel;

ALTER TABLE judge_report_rating
    DROP PRIMARY KEY;

ALTER TABLE judge_report_rating
    MODIFY fk_titel BIGINT NULL;

ALTER TABLE judge_report_rating
    ADD COLUMN
        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;

ALTER TABLE judge_report_rating
    ADD CONSTRAINT uq_judge_report_rating UNIQUE (fk_judge_report, fk_titel, category);
ALTER TABLE judge_report_rating
    ADD CONSTRAINT judge_report_rating_report FOREIGN KEY (fk_judge_report) REFERENCES judge_report (id);
ALTER TABLE judge_report_rating
    ADD CONSTRAINT judge_report_rating_titel FOREIGN KEY (fk_titel) REFERENCES titel (id);
