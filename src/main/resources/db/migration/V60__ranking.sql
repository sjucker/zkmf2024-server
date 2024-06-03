alter table ranking
    drop column fk_verein;
alter table ranking
    drop column score;
alter table ranking
    drop column rank;
alter table ranking
    add column location_identifier varchar(255) not null default '';
alter table ranking
    add column status varchar(255) not null default 'PENDING';

create table ranking_entry
(
    fk_ranking bigint        not null
        constraint fk_ranking_entry_ranking
            references ranking
            on delete cascade,
    fk_verein  bigint        not null
        constraint fk_ranking_entry_verein
            references verein
            on delete cascade,
    score      numeric(5, 2) not null,
    rank       int           not null
);
