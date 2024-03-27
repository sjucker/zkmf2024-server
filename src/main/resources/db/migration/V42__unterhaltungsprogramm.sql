create table unterhaltung_entry
(
    id            bigserial
        constraint pk_unterhaltung_entry primary key,
    entry_type    unterhaltung_entry_type not null,
    date          date                    not null,
    start_time    time                    not null,
    end_time      time                    null,
    title         varchar(255)            not null,
    subtitle      varchar(1024),
    fk_location   bigint                  not null
        constraint fk_unterhaltung_location references location on delete cascade,
    cloudflare_id varchar(255),
    text          varchar(4096)
);
