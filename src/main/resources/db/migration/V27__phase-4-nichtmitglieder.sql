create table verein_anmeldung_nichtmitglieder
(
    id         bigserial
        constraint pk_verein_anmeldung_nichtmitglieder primary key,
    fk_verein  bigint       not null
        constraint fk_verein_anmeldung_nichtmitglieder_verein references verein on delete cascade,
    amount     integer      not null,
    instrument varchar(255) not null
);
