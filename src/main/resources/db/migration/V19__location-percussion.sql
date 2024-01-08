alter table location
    add column percussion_equipment varchar(255) null;

-- @formatter:off
update location set percussion_equipment = 'STANDARD' where id = 19;
update location set percussion_equipment = 'FULL' where id = 15;
update location set percussion_equipment = 'STANDARD' where id = 16;
update location set percussion_equipment = 'FULL' where id = 14;
update location set percussion_equipment = 'STANDARD' where id = 17;
update location set percussion_equipment = 'FULL' where id = 18;
update location set percussion_equipment = 'STANDARD' where id = 21;
-- @formatter:on
