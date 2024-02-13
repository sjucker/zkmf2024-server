create table verein_anmeldung_detail
(
    id                          bigserial
        constraint pk_verein_anmeldung_detail primary key,
    fk_verein                   bigint  not null
        constraint fk_verein_anmeldung_detail_verein references verein on delete cascade,
    festfuehrer_amount          integer,
    festkarten_musiker_amount   integer,
    festkarten_begleiter_amount integer,
    freitagabend_amount         integer,
    gehbehinderung              boolean not null default false,
    partituren_sent             boolean not null default false,
    partituren_sent_at          date,
    gesamtchor                  boolean not null default false,
    adhoc_orchester             boolean not null default false,
    anreise_public_transport    boolean not null default false,
    anreise_otherwise           varchar(1024),
    verpflegung_meat            integer,
    verpflegung_vegan           integer,
    verpflegung_allergies       integer,
    verpflegung_none            integer,
    verpflegung_helper          boolean not null default false,
    verpflegung_helper_1        varchar(255),
    verpflegung_helper_2        varchar(255),
    verpflegung_helper_3        varchar(255),
    verpflegung_helper_4        varchar(255),
    verpflegung_helper_5        varchar(255),
    verpflegung_helper_6        varchar(255)
);

insert into verein_anmeldung_detail (fk_verein)
select id
from verein;

create table verein_anmeldung_adhoc_orchester
(
    id         bigserial
        constraint pk_verein_anmeldung_adhoc_orchester primary key,
    fk_verein  bigint       not null
        constraint fk_verein_anmeldung_adhoc_orchester_verein references verein on delete cascade,
    name       varchar(255) not null,
    email      varchar(255) not null,
    instrument varchar(255) not null
);
