create table currently_playing
(
    id                 bigserial
        constraint pk_currently_playing primary key,
    fk_timetable_entry bigint    not null
        constraint fk_timetable_entry
            references timetable_entry
            on delete cascade,
    started_at         timestamp not null,
    ended_at           timestamp null
);
