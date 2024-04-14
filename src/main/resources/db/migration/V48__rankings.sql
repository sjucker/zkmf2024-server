create table ranking
(
    id        bigserial     not null
        constraint pk_ranking primary key,
    fk_verein bigint        not null
        constraint fk_ranking_verein
            references verein
            on delete cascade,
    modul     varchar(255)  not null,
    klasse    varchar(255)  null,
    besetzung varchar(255)  null,
    category  varchar(255)  null,
    score     numeric(5, 2) not null,
    rank      int           not null
);
