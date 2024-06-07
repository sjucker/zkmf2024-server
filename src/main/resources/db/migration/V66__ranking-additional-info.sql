alter table ranking
    add column day date null;

alter table ranking_entry
    add column additional_info varchar(255) null;
