create table festprogramm_entry
(
    id          bigserial
        constraint pk_festprogramm_entry primary key,
    date        date          not null,
    start_time  time          not null,
    description varchar(1024) not null,
    location    varchar(255)  not null,
    important   boolean       not null default false
);
