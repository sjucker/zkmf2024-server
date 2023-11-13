create table errata
(
    id        bigserial
        constraint pk_errata primary key,
    modul     varchar(255) not null,
    klasse    varchar(255) not null,
    besetzung varchar(255) not null,
    text      text,
    constraint uq_errata unique (modul, klasse, besetzung)
);

insert into errata (modul, klasse, besetzung, text)
values ('A', 'HOECHSTKLASSE', 'HARMONIE', null),
       ('A', 'KLASSE_1', 'HARMONIE', null),
       ('A', 'KLASSE_2', 'HARMONIE', null),
       ('A', 'KLASSE_3', 'HARMONIE', null),
       ('A', 'KLASSE_4', 'HARMONIE', null),
       ('A', 'HOECHSTKLASSE', 'BRASS_BAND', null),
       ('A', 'KLASSE_1', 'BRASS_BAND', null),
       ('A', 'KLASSE_2', 'BRASS_BAND', null),
       ('A', 'KLASSE_3', 'BRASS_BAND', null),
       ('A', 'KLASSE_4', 'BRASS_BAND', null),
       ('B', 'OBERSTUFE', 'HARMONIE', null),
       ('B', 'MITTELSTUFE', 'HARMONIE', null),
       ('B', 'UNTERSTUFE', 'HARMONIE', null),
       ('B', 'OBERSTUFE', 'BRASS_BAND', null),
       ('B', 'MITTELSTUFE', 'BRASS_BAND', null),
       ('B', 'UNTERSTUFE', 'BRASS_BAND', null);

