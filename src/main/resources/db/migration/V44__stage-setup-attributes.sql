alter table verein_anmeldung_detail
    add column stage_dirigentenpodest boolean not null default false;

alter table verein_anmeldung_detail
    add column stage_ablagen_amount integer;
