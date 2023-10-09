create type timetable_entry_type as enum ('EINSPIEL', 'WETTSPIEL', 'BESPRECHUNG', 'PLATZKONZERT', 'MARSCHMUSIK');

alter table timetable_entry
    add column entry_type timetable_entry_type not null default 'WETTSPIEL';

alter table timetable_entry
    add constraint uq_timetable_entry unique (fk_verein, fk_verein_programm, entry_type);
