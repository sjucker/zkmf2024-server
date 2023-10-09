create table verein_message
(
    id         bigserial
        constraint pk_verein_message primary key,
    fk_verein  bigint
        constraint fk_verein_message_verein references verein on delete cascade,
    message    varchar(8000) not null,
    created_at timestamp     not null,
    created_by varchar(255)  not null
);

create index idx_fk_verein_message_verein
    on verein_message (fk_verein);
