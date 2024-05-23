create table notification_sent
(
    id      varchar(255)
        constraint pk_notification_sent primary key,
    sent_at timestamp not null
);
