alter table ranking
    drop column location_identifier;

delete
from ranking;

alter table ranking
    add column fk_location bigint not null
        constraint fk_ranking_location
            references location
            on delete cascade;

alter table ranking_entry
    add constraint pk_ranking_entry primary key (fk_ranking, fk_verein);
