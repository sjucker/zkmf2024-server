alter table location
    add column identifier varchar(255);

-- @formatter:off
update location set identifier = 'singsaal-moosmatt' where id = 1;
update location set identifier = 'singkeller-bahnhofstrasse' where id = 2;
update location set identifier = 'singsaal-weihermatt' where id = 3;
update location set identifier = 'freiestrasse-seminarraeume' where id = 4;
update location set identifier = 'schulhaus-hofacker-singsaal' where id = 5;
update location set identifier = 'turnhalle-embri' where id = 6;
update location set identifier = 'turnhalle-bahnhofstrasse' where id = 7;
update location set identifier = 'schulhaus-hofacker-turnhalle' where id = 8;
update location set identifier = 'foyer-schulhaus-embri' where id = 9;
update location set identifier = 'jugendtreff-ref-kirche' where id = 10;
update location set identifier = 'schulzimmer-weihermatt' where id = 11;
update location set identifier = 'alte-ref-kirche' where id = 12;
update location set identifier = 'altes-schulhaus' where id = 13;
update location set identifier = 'embrisaal-urdorf' where id = 14;
update location set identifier = 'zentrumshalle-urdorf' where id = 15;
update location set identifier = 'reformierte-kirche-urdorf' where id = 16;
update location set identifier = 'turnhalle-weihermatt-urdorf' where id = 17;
update location set identifier = 'salmensaal-schlieren' where id = 18;
update location set identifier = 'grosse-ref-kirche-schlieren' where id = 19;
update location set identifier = 'marschmusikstrecke-urdorf' where id = 20;
update location set identifier = 'festzelt-schlieren' where id = 21;
update location set identifier = 'festzelt-urdorf' where id = 22;
update location set identifier = 'zivilschutzanlage' where id = 23;
update location set identifier = 'schulhaus-bahnhofstrasse' where id = 24;
-- @formatter:on

alter table location
    add constraint uq_location_identifier unique (identifier);
