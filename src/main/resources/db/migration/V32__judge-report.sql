CREATE TABLE judge_report
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    fk_judge           BIGINT NOT NULL,
    fk_timetable_entry BIGINT NOT NULL,
    score              INT,
    CONSTRAINT pk_judge_report PRIMARY KEY (id),
    CONSTRAINT uq_judge_report UNIQUE (fk_judge, fk_timetable_entry),
    CONSTRAINT fk_judge_report_judge FOREIGN KEY (fk_judge) REFERENCES judge (id),
    CONSTRAINT fk_judge_report_timetable FOREIGN KEY (fk_timetable_entry) REFERENCES timetable_entry (id)
);
