alter table ranking_entry
    add column confirmed_by varchar(255);
alter table ranking_entry
    add column confirmed_at timestamp;
