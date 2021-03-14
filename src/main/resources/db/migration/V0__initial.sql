create table band
(
    id               int           not null auto_increment,
    email            varchar(255)  not null,
    name             varchar(1024) not null,
    contactFirstName varchar(255)  not null,
    contactLastName  varchar(255)  not null,
    primary key (id),
    unique (email)
);
