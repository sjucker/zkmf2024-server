alter table judge_report
    drop constraint if exists judge_report_fk_judge_fk_timetable_entry_key;

alter table judge_report
    add column category varchar(255) null;

alter table judge_report
    add constraint uq_judge_report unique (fk_judge, fk_timetable_entry, category);
