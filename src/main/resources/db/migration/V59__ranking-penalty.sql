create table ranking_penalty
(
    id                 bigserial
        constraint pk_ranking_penalty primary key,
    fk_timetable_entry bigint  not null
        constraint fk_timetable_entry
            references timetable_entry
            on delete cascade,
    minutes_overrun    integer not null
);

