alter table app_page
    add column created_at timestamp not null default now();
