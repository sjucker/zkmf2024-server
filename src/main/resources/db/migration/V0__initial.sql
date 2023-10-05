create type location_location_type as enum ('PARADEMUSIK', 'EINSPIELLOKAL', 'INSTRUMENTENDEPOT', 'WETTSPIELLOKAL', 'JURYFEEDBACK');

create table helper_registration
(
    id                    bigserial
        constraint pk_helper_registration
            primary key,
    vorname               varchar(255)             not null,
    name                  varchar(255)             not null,
    email                 varchar(255)             not null unique,
    telefon               varchar(255)             not null,
    comment               varchar(1024),
    adresse               varchar(255)             not null,
    plz_ort               varchar(255)             not null,
    geburtsdatum          date                     not null,
    vereinszugehoerigkeit varchar(1024)            not null,
    aufgaben              varchar(1024)            not null,
    anzahl_einsaetze      varchar(1024)            not null,
    einsatz_mittwoch      varchar(1024)            not null,
    einsatz_donnerstag    varchar(1024)            not null,
    einsatz_freitag       varchar(1024)            not null,
    einsatz_samstag       varchar(1024)            not null,
    einsatz_sonntag       varchar(1024)            not null,
    einsatz_montag        varchar(1024)            not null,
    einsatz_dienstag      varchar(1024)            not null,
    groesse_shirt         varchar(24)              not null,
    registered_at         timestamp with time zone not null
);

create table image
(
    id          bigserial
        constraint pk_image
            primary key,
    foreign_key bigint                   not null,
    content     bytea,
    name        varchar(255)             not null,
    uploaded_at timestamp with time zone not null,
    type        varchar(255)             not null
);

create table judge
(
    id    bigserial
        constraint pk_judge
            primary key,
    email varchar(255) not null unique,
    name  varchar(255) not null
);

create table kontakt
(
    id             bigserial
        constraint pk_kontakt
            primary key,
    vorname        varchar(255),
    nachname       varchar(255),
    adresse        varchar(255),
    plz            integer,
    ort            varchar(255),
    email          varchar(255),
    telefon_privat varchar(255),
    telefon_mobile varchar(255)
);

create table location
(
    id                   bigserial
        constraint pk_location
            primary key,
    name                 varchar(255)    not null,
    address              varchar(255),
    latitude             numeric(16, 12) not null,
    longitude            numeric(16, 12) not null,
    location_type        location_location_type,
    capacity             varchar(255),
    modules              varchar(255),
    einspiellokal_id     bigint
        constraint fk_location_einspiellokal
            references location,
    instrumentendepot_id bigint
        constraint fk_location_instrumentendepot
            references location,
    juryfeedback_id      bigint
        constraint fk_location_juryfeedback
            references location
);

create index idx_fk_location_juryfeedback
    on location (juryfeedback_id);

create index idx_fk_location_einspiellokal
    on location (einspiellokal_id);

create index idx_fk_location_instrumentendepot
    on location (instrumentendepot_id);

create table newsletter_recipient
(
    email           varchar(255)             not null
        constraint pk_newsletter_recipient
            primary key,
    vorname         varchar(255),
    name            varchar(255)             not null,
    subscribed_at   timestamp with time zone not null,
    unsubscribed_at timestamp with time zone
);

create table programm_vorgaben
(
    modul                   varchar(255) not null,
    klasse                  varchar(255) not null,
    besetzung               varchar(255) not null,
    min_duration_in_seconds integer      not null,
    max_duration_in_seconds integer      not null,
    constraint pk_programm_vorgaben
        primary key (modul, klasse, besetzung)
);

create table survey_answer
(
    id                     bigserial
        constraint pk_survey_answer
            primary key,
    timestamp              timestamp with time zone not null,
    vereins_name           varchar(255)             not null,
    besetzung              varchar(255)             not null,
    staerke_klasse         varchar(255)             not null,
    anzahl_mitglieder      varchar(255)             not null,
    kontakt_name           varchar(255)             not null,
    kontakt_email          varchar(255)             not null,
    kontakt_telefon        varchar(255)             not null,
    modul_auswahl          varchar(255),
    zusage_kommentar       varchar(1024),
    absage                 boolean,
    absage_kommentar       varchar(1024),
    absage_kontaktaufnahme varchar(255),
    helfer                 varchar(255)
);

create table verein
(
    id                             bigserial
        constraint pk_verein
            primary key,
    email                          varchar(255) not null,
    praesident_kontakt_id          bigint       not null
        constraint fk_verein_praesident_kontakt
            references kontakt,
    direktion_kontakt_id           bigint       not null
        constraint fk_verein_direktion_kontakt
            references kontakt,
    vereinsname                    varchar(255),
    adresse                        varchar(255),
    plz                            integer,
    ort                            varchar(255),
    homepage                       varchar(255),
    facebook                       varchar(255),
    instagram                      varchar(255),
    iban                           varchar(255),
    modula                         boolean      not null,
    modulb                         boolean      not null,
    modulc                         boolean      not null,
    moduld                         boolean      not null,
    module                         boolean      not null,
    modulf                         boolean      not null,
    modulg                         boolean      not null,
    modulh                         boolean      not null,
    klasse_modula                  varchar(255),
    klasse_modulb                  varchar(255),
    klasse_modulh                  varchar(255),
    harmonie                       boolean      not null,
    brass_band                     boolean      not null,
    fanfare                        boolean      not null,
    tambouren                      boolean      not null,
    perkussionsensemble            boolean      not null,
    website_text                   varchar(4096),
    direktion_doppeleinsatz        boolean      not null,
    direktion_doppeleinsatz_verein varchar(255),
    mitspieler_doppeleinsatz       boolean      not null,
    confirmed_at                   timestamp with time zone,
    tambouren_kat_a                boolean      not null,
    tambouren_kat_b                boolean      not null,
    tambouren_kat_c                boolean      not null,
    phase2_confirmed_by            varchar(255),
    phase2_confirmed_at            timestamp with time zone,
    prov_wettspiel                 varchar(255),
    prov_parademusik               varchar(255),
    prov_platzkonzert              varchar(255)
);

create table titel
(
    id                  bigserial
        constraint pk_titel
            primary key,
    fk_verein           bigint
        constraint fk_titel_verein
            references verein
            on delete cascade,
    titel_name          varchar(255) not null,
    composer            varchar(255) not null,
    arrangeur           varchar(255),
    grad                numeric(2, 1),
    duration_in_seconds integer      not null,
    modul               varchar(255) not null,
    klasse              varchar(255),
    besetzung           varchar(255),
    info_moderation     varchar(4096),
    schwierigkeitsgrad  varchar(255)
);

create index idx_fk_titel_verein
    on titel (fk_verein);

create index idx_fk_verein_praesident_kontakt
    on verein (praesident_kontakt_id);

create index idx_fk_verein_direktion_kontakt
    on verein (direktion_kontakt_id);

create table verein_comment
(
    id         bigserial
        constraint pk_verein_comment
            primary key,
    fk_verein  bigint
        constraint fk_verein_comment_verein
            references verein
            on delete cascade,
    comment    varchar(8000)            not null,
    created_at timestamp with time zone not null,
    created_by varchar(255)             not null
);

create index idx_fk_verein_comment_verein
    on verein_comment (fk_verein);

create table verein_doppeleinsatz
(
    id              bigserial
        constraint pk_verein_doppeleinsatz
            primary key,
    fk_verein       bigint       not null
        constraint fk_verein_doppeleinsatz_verein
            references verein
            on delete cascade,
    fk_other_verein bigint       not null
        constraint fk_verein_doppeleinsatz_other_verein
            references verein
            on delete cascade,
    name            varchar(255) not null
);

create index idx_fk_verein_doppeleinsatz_other_verein
    on verein_doppeleinsatz (fk_other_verein);

create index idx_fk_verein_doppeleinsatz_verein
    on verein_doppeleinsatz (fk_verein);

create table verein_programm
(
    id                        bigserial
        constraint pk_verein_programm
            primary key,
    fk_verein                 bigint       not null
        constraint fk_verein_programm_verein
            references verein
            on delete cascade,
    modul                     varchar(255) not null,
    klasse                    varchar(255),
    besetzung                 varchar(255),
    titel                     varchar(255),
    info_moderation           varchar(8192),
    total_duration_in_seconds integer,
    modul_g_kat_a_1           varchar(255),
    modul_g_kat_a_2           varchar(255),
    modul_g_kat_a_titel_1_id  bigint,
    modul_g_kat_a_titel_2_id  bigint,
    modul_g_kat_b_titel_id    bigint,
    modul_g_kat_c_titel_id    bigint,
    modul_b_pa                boolean      not null,
    modul_b_egitarre          boolean      not null,
    modul_b_ebass             boolean      not null,
    modul_b_keyboard          boolean      not null,
    modul_b_gesang            boolean      not null,
    modul_d_titel_1_id        bigint,
    modul_d_titel_2_id        bigint
);

create table timetable_entry
(
    id                 bigserial
        constraint pk_timetable_entry
            primary key,
    fk_verein          bigint not null
        constraint fk_timetable_verein
            references verein
            on delete cascade,
    fk_verein_programm bigint
        constraint fk_timetable_programm
            references verein_programm
            on delete cascade,
    fk_location        bigint not null
        constraint fk_timetable_location
            references location
            on delete cascade,
    date               date   not null,
    start_time         time   not null,
    end_time           time   not null,
    unique (fk_verein, fk_verein_programm)
);

create table judge_report
(
    id                 bigserial
        constraint pk_judge_report
            primary key,
    fk_judge           bigint                                        not null
        constraint fk_judge_report_judge
            references judge,
    fk_timetable_entry bigint                                        not null
        constraint fk_judge_report_timetable
            references timetable_entry,
    score              integer,
    status             varchar(255) default 'NEW'::character varying not null,
    finished_at        timestamp with time zone,
    unique (fk_judge, fk_timetable_entry)
);

create index idx_fk_judge_report_timetable
    on judge_report (fk_timetable_entry);

create table judge_report_comment
(
    fk_judge_report bigint not null
        constraint fk_judge_report_comment_report
            references judge_report,
    fk_titel        bigint not null
        constraint fk_judge_report_comment_titel
            references titel,
    comment         varchar(4096),
    constraint pk_judge_report_comment
        primary key (fk_judge_report, fk_titel)
);

create index idx_fk_judge_report_comment_titel
    on judge_report_comment (fk_titel);

create table judge_report_rating
(
    id              bigserial
        constraint pk_judge_report_rating
            primary key,
    fk_judge_report bigint       not null
        constraint fK_judge_report_rating_report
            references judge_report,
    fk_titel        bigint
        constraint fk_judge_report_rating_titel
            references titel,
    category        varchar(255) not null,
    rating          varchar(255) not null,
    comment         varchar(1024),
    unique (fk_judge_report, fk_titel, category)
);

create index idx_fk_judge_report_rating_titel
    on judge_report_rating (fk_titel);

create index idx_fk_timetable_location
    on timetable_entry (fk_location);

create index idx_fk_timetable_programm
    on timetable_entry (fk_verein_programm);

create index idx_fk_verein_programm_verein
    on verein_programm (fk_verein);

create table verein_programm_titel
(
    fk_programm         bigint  not null
        constraint fk_verein_programm_titel_programm
            references verein_programm
            on delete cascade,
    fk_titel            bigint  not null
        constraint fk_verein_programm_titel_programm_titel
            references titel
            on delete cascade,
    position            integer not null,
    duration_in_seconds integer,
    applaus_in_seconds  integer,
    constraint pk_verein_programm_titel
        primary key (fk_programm, fk_titel),
    unique (fk_programm, fk_titel, position)
);

create index idx_fk_verein_programm_titel_titel
    on verein_programm_titel (fk_titel);

create table verein_status
(
    fk_verein bigint                                        not null
        constraint pk_verein_status
            primary key
        constraint fk_verein_status
            references verein
            on delete cascade,
    phase1    varchar(255) default 'NEW'::character varying not null,
    phase2    varchar(255) default 'NEW'::character varying not null
);

create table zkmf2024_user
(
    email                varchar(255)                                         not null
        constraint pk_zkmf2024_user
            primary key,
    role                 varchar(255)                                         not null,
    password             varchar(255) default '{noop}pass'::character varying not null,
    last_login           timestamp with time zone,
    created_at           timestamp with time zone,
    email_verification   varchar(255),
    email_verified_at    timestamp with time zone,
    password_reset_token varchar(255)
);


INSERT INTO titel (fk_verein, titel_name, composer, duration_in_seconds, modul, klasse, besetzung)
VALUES (null, 'Traveler', 'David Maslanka', 796, 'A', 'HOECHSTKLASSE', 'HARMONIE'),
       (null, 'La Mezquita de Cordoba', 'Julie Giroux', 701, 'A', 'KLASSE_1', 'HARMONIE'),
       (null, 'QuaranTime', 'Stephan Hodel', 459, 'A', 'KLASSE_2', 'HARMONIE'),
       (null, 'May the Forest Bloom Again', 'Théo Schmitt', 398, 'A', 'KLASSE_3', 'HARMONIE'),
       (null, 'Samurai', 'Mathias Wehr', 345, 'A', 'KLASSE_4', 'HARMONIE'),

       (null, 'Metropolis 1927', 'Peter Graham', 950, 'A', 'HOECHSTKLASSE', 'BRASS_BAND'),
       (null, 'Circius', 'Torstein Aagard-Nilsen', 542, 'A', 'KLASSE_1', 'BRASS_BAND'),
       (null, 'Airs and Dances', 'Alan Fernie', 446, 'A', 'KLASSE_2', 'BRASS_BAND'),
       (null, 'The Pioneers', 'Philip Sparke', 345, 'A', 'KLASSE_3', 'BRASS_BAND'),
       (null, 'Samurai', 'Mathias Wehr', 345, 'A', 'KLASSE_4', 'BRASS_BAND'),

       (null, 'Halvdan Sivertsen Medley', 'Lars Erik Gudim', 390, 'B', 'OBERSTUFE', 'HARMONIE'),
       (null, 'We''ll Make It Work', 'Christoph Walter', 300, 'B', 'MITTELSTUFE', 'HARMONIE'),
       (null, 'On a Roll', 'Oliver Waespi', 250, 'B', 'UNTERSTUFE', 'HARMONIE'),

       (null, 'Windows of the World (1. Satz)', 'Peter Graham', 110, 'B', 'OBERSTUFE', 'BRASS_BAND'),
       (null, 'Windows of the World (3. Satz)', 'Peter Graham', 230, 'B', 'OBERSTUFE', 'BRASS_BAND'),
       (null, 'We''ll Make It Work', 'Christoph Walter', 300, 'B', 'MITTELSTUFE', 'BRASS_BAND'),
       (null, 'On a Roll', 'Oliver Waespi', 250, 'B', 'UNTERSTUFE', 'BRASS_BAND');

INSERT INTO programm_vorgaben (modul, klasse, besetzung, min_duration_in_seconds, max_duration_in_seconds)
VALUES ('A', 'HOECHSTKLASSE', 'HARMONIE', 50 * 60, 60 * 60),
       ('A', 'KLASSE_1', 'HARMONIE', 35 * 60, 45 * 60),
       ('A', 'KLASSE_2', 'HARMONIE', 25 * 60, 30 * 60),
       ('A', 'KLASSE_3', 'HARMONIE', 20 * 60, 25 * 60),
       ('A', 'KLASSE_4', 'HARMONIE', 18 * 60, 22 * 60),

       ('A', 'HOECHSTKLASSE', 'BRASS_BAND', 50 * 60, 60 * 60),
       ('A', 'KLASSE_1', 'BRASS_BAND', 35 * 60, 45 * 60),
       ('A', 'KLASSE_2', 'BRASS_BAND', 25 * 60, 30 * 60),
       ('A', 'KLASSE_3', 'BRASS_BAND', 20 * 60, 25 * 60),
       ('A', 'KLASSE_4', 'BRASS_BAND', 18 * 60, 22 * 60),

       ('B', 'OBERSTUFE', 'HARMONIE', 20 * 60, 25 * 60),
       ('B', 'MITTELSTUFE', 'HARMONIE', 15 * 60, 20 * 60),
       ('B', 'UNTERSTUFE', 'HARMONIE', 10 * 60, 15 * 60),

       ('B', 'OBERSTUFE', 'BRASS_BAND', 20 * 60, 25 * 60),
       ('B', 'MITTELSTUFE', 'BRASS_BAND', 15 * 60, 20 * 60),
       ('B', 'UNTERSTUFE', 'BRASS_BAND', 10 * 60, 15 * 60),

       ('H', 'OBERSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60),
       ('H', 'MITTELSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60),
       ('H', 'UNTERSTUFE', 'PERKUSSIONSENSEMBLE', 8 * 60, 10 * 60);

INSERT INTO location (id, name, address, latitude, longitude, location_type)
VALUES (1, 'Singsaal Moosmatt', 'Im Moos 24, 8902 Urdorf', '47.388554561192244', '8.42519240835916', 'EINSPIELLOKAL'),
       (2, 'Singkeller Bahnhofstrasse', 'Bahnhofstrasse 50, 8902 Urdorf', '47.386638276949924', '8.42506582494283',
        'EINSPIELLOKAL'),
       (3, 'Singsaal Weihermatt', 'Weihermattstrasse 50, 8902 Urdorf', '47.38047862379699', '8.426408538143013',
        'EINSPIELLOKAL'),
       (4, 'Freiestrasse Seminarräume', 'Freiestrasse 23, 8952 Schlieren', '47.395522952968484', '8.444371227607338',
        'EINSPIELLOKAL'),
       (5, 'Schulhaus Hofacker Singsaal', 'Hofackerstrasse 2, 8952 Schlieren', '47.39462535997627', '8.444605036975226',
        'EINSPIELLOKAL'),
       (6, 'Turnhalle Embri', 'Im Embri 8, 8902 Urdorf', '47.38703624125738', '8.42564926008073',
        'INSTRUMENTENDEPOT'),
       (7, 'Turnhalle Bahnhofstrasse', 'Bahnhofstrasse 50, 8902 Urdorf', '47.38657135340039', '8.42518917642805',
        'INSTRUMENTENDEPOT'),
       (8, 'Schulhaus Hofacker Turnhalle', 'Bahnhofstrasse 50, 8902 Urdorf', '47.39429711026217',
        '8.443844979990686',
        'INSTRUMENTENDEPOT'),
       (9, 'Foyer Schulhaus Embri', 'Im Embri 49, 8902 Urdorf', '47.38731700999835', '8.424525947622346',
        'JURYFEEDBACK'),
       (10, 'Jugendtreff ref. Kirche', 'Weihermattstrasse 40, 8902 Urdorf', '47.38185865708794', '8.424578083572106',
        'JURYFEEDBACK'),
       (11, 'Weihermatt Schulzimmer', 'Weihermattstrasse 50, 8902 Urdorf', '47.380156341435075', '8.426453046746492',
        'JURYFEEDBACK'),
       (12, 'Kapelle', 'Kirchgasse 5, 8952 Schlieren', '47.396504093983935', '8.44571544473399', 'JURYFEEDBACK'),
       (13, 'Altes Schulhaus', 'Freiestrasse 10, 8952 Schlieren', '47.39578229774573', '8.44650256507529',
        'JURYFEEDBACK');

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
VALUES (14, 'Embrisaal', 'Im Embri 8, 8902 Urdorf', '47.38706899770157', '8.425614397928756', 'WETTSPIELLOKAL',
        '10 Personen / 16 Slots', 'G (Tambouren) und H (Perkussionsensembles)',
        2, 6, 9),
       (15, 'Zentrumshalle', 'Birmensdorferstrasse 77, 8902 Urdorf', '47.385636902376085', '8.422329961060896',
        'WETTSPIELLOKAL',
        '80 Personen / 12 Slots', 'A (Konzertmusik) Höchstklasse und 1. Klasse, Harmonie und Brass Band',
        1, 7, null),
       (16, 'Reformierte Kirche Urdorf', 'Weihermattstrasse 40, 8902 Urdorf', '47.38187985073541',
        '8.424846238635222',
        'WETTSPIELLOKAL',
        '50 Personen / 14 Slots', 'A (Konzertmusik) 3. und 4. Klasse, Harmonie und Brass Band',
        null, 7, 10),
       (17, 'Turnhalle Weihermatt', 'Weihermattstrasse 50, 8902 Urdorf', '47.37993604476329', '8.42733046833702',
        'WETTSPIELLOKAL',
        '70 Personen / 21 Slots', 'B (Unterhaltungsmusik) alle Stufen, Harmonie und Brass Band',
        3, 7, 11),
       (18, 'Salmensaal', 'Uitikonerstrasse 17, 8952 Schlieren', '47.396055051973725', '8.44862519659661',
        'WETTSPIELLOKAL',
        '70 Personen / 18 Slots', 'A (Konzertmusik) 2. Klasse, Harmonie und Brass Band',
        5, 8, 13),
       (19, 'Reformierte Kirche Schlieren', 'Kirchgasse 5, 8952 Schlieren', '47.39680101453343', '8.445732978150247',
        'WETTSPIELLOKAL',
        '50 Personen / 20 Slots', 'A (Konzertmusik) 3. Klasse, ausschliesslich Harmonie',
        4, null, 12),
       (20, 'Parademusik Traditionell', 'Im Moos 2, 8902 Urdorf', '47.3908502804669', '8.427807617040116',
        'PARADEMUSIK', null, null, null, null, null);
