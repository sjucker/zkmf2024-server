alter table app_page
    add column title varchar(255) not null default '';
alter table app_page
    add column news boolean not null default false;
