create table screen
(
    id                  bigserial
        constraint pk_screen primary key,
    location_identifier varchar(255) null,
    header              text         null,
    message             text         null,
    cloudflare_id       varchar(255) null,
    active              boolean      not null default false
)
