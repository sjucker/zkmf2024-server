create table app_page
(
    id            bigserial not null
        constraint pk_app_page primary key,
    markdown      text      not null,
    cloudflare_id varchar(255)
);
