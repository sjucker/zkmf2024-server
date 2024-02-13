alter table verein_status
    add column phase4 varchar(255) default 'NEW'::character varying not null;
