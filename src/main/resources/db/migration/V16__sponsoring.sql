create table sponsor
(
    id            bigserial
        constraint pk_sponsor primary key,
    type          varchar(255) not null,
    name          varchar(255) not null,
    cloudflare_id varchar(255) not null,
    url           varchar(255)
);
