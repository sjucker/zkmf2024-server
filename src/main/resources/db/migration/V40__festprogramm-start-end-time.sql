alter table festprogramm_entry
    drop column end_time;

alter table festprogramm_entry
    add column time_description varchar(255);
