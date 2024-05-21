create table emergency_message
(
    id      bigserial
        constraint pk_emergency_message primary key,
    header  text,
    message text,
    active  boolean not null default false
);
