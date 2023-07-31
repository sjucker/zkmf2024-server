ALTER TABLE location
    ADD COLUMN location_type ENUM ('PARADEMUSIK', 'EINSPIELLOKAL', 'INSTRUMENTENDEPOT','WETTSPIELLOKAL', 'JURYFEEDBACK');
ALTER TABLE location
    ADD COLUMN capacity VARCHAR(255);
ALTER TABLE location
    ADD COLUMN modules VARCHAR(255);
ALTER TABLE location
    ADD COLUMN einspiellokal_id BIGINT;
ALTER TABLE location
    ADD COLUMN instrumentendepot_id BIGINT;
ALTER TABLE location
    ADD COLUMN juryfeedback_id BIGINT;

ALTER TABLE location
    ADD CONSTRAINT FK_LOCATION_EINSPIELLOKAL FOREIGN KEY (einspiellokal_id) REFERENCES location (id);
ALTER TABLE location
    ADD CONSTRAINT FK_LOCATION_INSTRUMENTENDEPOT FOREIGN KEY (instrumentendepot_id) REFERENCES location (id);
ALTER TABLE location
    ADD CONSTRAINT FK_LOCATION_JURYFEEDBACK FOREIGN KEY (juryfeedback_id) REFERENCES location (id);

-- Einspiellokale
INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (1, 'Singsaal Moosmatt', 'Im Moos 24, 8902 Urdorf', '47.388554561192244', '8.42519240835916',
           'EINSPIELLOKAL');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (2, 'Singkeller Bahnhofstrasse', 'Bahnhofstrasse 50, 8902 Urdorf', '47.386638276949924', '8.42506582494283',
           'EINSPIELLOKAL');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (3, 'Singsaal Weihermatt', 'Weihermattstrasse 50, 8902 Urdorf', '47.38047862379699', '8.426408538143013',
           'EINSPIELLOKAL');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (4, 'Freiestrasse Seminarräume', 'Freiestrasse 23, 8952 Schlieren', '47.395522952968484', '8.444371227607338',
           'EINSPIELLOKAL');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (5, 'Schulhaus Hofacker Singsaal', 'Hofackerstrasse 2, 8952 Schlieren', '47.39462535997627',
           '8.444605036975226',
           'EINSPIELLOKAL');

-- Instrumentendepot
INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (6, 'Turnhalle Embri', 'Im Embri 8, 8902 Urdorf', '47.38703624125738', '8.42564926008073',
           'INSTRUMENTENDEPOT');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (7, 'Turnhalle Bahnhofstrasse', 'Bahnhofstrasse 50, 8902 Urdorf', '47.38657135340039', '8.42518917642805',
           'INSTRUMENTENDEPOT');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (8, 'Schulhaus Hofacker Turnhalle', 'Bahnhofstrasse 50, 8902 Urdorf', '47.39429711026217',
           '8.443844979990686',
           'INSTRUMENTENDEPOT');

-- Juryfeedback
INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (9, 'Foyer Schulhaus Embri', 'Im Embri 49, 8902 Urdorf', '47.38731700999835', '8.424525947622346',
           'JURYFEEDBACK');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (10, 'Jugendtreff ref. Kirche', 'Weihermattstrasse 40, 8902 Urdorf', '47.38185865708794', '8.424578083572106',
           'JURYFEEDBACK');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (11, 'Weihermatt Schulzimmer', 'Weihermattstrasse 50, 8902 Urdorf', '47.380156341435075', '8.426453046746492',
           'JURYFEEDBACK');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (12, 'Kapelle', 'Kirchgasse 5, 8952 Schlieren', '47.396504093983935', '8.44571544473399', 'JURYFEEDBACK');

INSERT INTO location (id, name, address, latitude, longitude, location_type)
    VALUE (13, 'Altes Schulhaus', 'Freiestrasse 10, 8952 Schlieren', '47.39578229774573', '8.44650256507529',
           'JURYFEEDBACK');

-- Wettspiellokal
INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (14, 'Embrisaal', 'Im Embri 8, 8902 Urdorf', '47.38706899770157', '8.425614397928756', 'WETTSPIELLOKAL',
           '10 Personen / 16 Slots', 'G (Tambouren) und H (Perkussionsensembles)',
           2, 6, 9);

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (15, 'Zentrumshalle', 'Birmensdorferstrasse 77, 8902 Urdorf', '47.385636902376085', '8.422329961060896',
           'WETTSPIELLOKAL',
           '80 Personen / 12 Slots', 'A (Konzertmusik) Höchstklasse und 1. Klasse, Harmonie und Brass Band',
           1, 7, null);

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (16, 'Reformierte Kirche Urdorf', 'Weihermattstrasse 40, 8902 Urdorf', '47.38187985073541',
           '8.424846238635222',
           'WETTSPIELLOKAL',
           '50 Personen / 14 Slots', 'A (Konzertmusik) 3. und 4. Klasse, Harmonie und Brass Band',
           null, 7, 10);

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (17, 'Turnhalle Weihermatt', 'Weihermattstrasse 50, 8902 Urdorf', '47.37993604476329', '8.42733046833702',
           'WETTSPIELLOKAL',
           '70 Personen / 21 Slots', 'B (Unterhaltungsmusik) alle Stufen, Harmonie und Brass Band',
           3, 7, 11);

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (18, 'Salmensaal', 'Uitikonerstrasse 17, 8952 Schlieren', '47.396055051973725', '8.44862519659661',
           'WETTSPIELLOKAL',
           '70 Personen / 18 Slots', 'A (Konzertmusik) 2. Klasse, Harmonie und Brass Band',
           5, 8, 13);

INSERT INTO location (id, name, address, latitude, longitude, location_type, capacity, modules, einspiellokal_id,
                      instrumentendepot_id, juryfeedback_id)
    VALUE (19, 'Reformierte Kirche Schlieren', 'Kirchgasse 5, 8952 Schlieren', '47.39680101453343', '8.445732978150247',
           'WETTSPIELLOKAL',
           '50 Personen / 20 Slots', 'A (Konzertmusik) 3. Klasse, ausschliesslich Harmonie',
           4, null, 12);
