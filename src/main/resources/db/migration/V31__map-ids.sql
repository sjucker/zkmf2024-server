insert into location (id, name, address, latitude, longitude, location_type, map_id)
values (27, 'Schulhaus Bahnhofstrasse', 'Bahnhofstrasse 52, 8902 Urdorf', 47.386159594962606, 8.425472326314559, 'EINSPIELLOKAL', '5E');

alter table location
    alter column map_id type varchar(5);

-- @formatter:off
update location set map_id = '2E' where id = 4;
update location set map_id = '1E' where id = 5;
update location set map_id = '1W' where id = 18;
update location set map_id = '2W' where id = 19;
update location set map_id = '1J' where id = 13;
update location set map_id = '2J', name = 'Alte ref. Kirche' where id = 12;
update location set map_id = '1/2D' where id = 8;
update location set map_id = '6W' where id = 17;
update location set map_id = '6J' where id = 11;
update location set map_id = '6E' where id = 3;
update location set map_id = '5W' where id = 16;
update location set map_id = '5J' where id = 10;
update location set map_id = '5E' where id = 10;
update location set map_id = '4W' where id = 15;
update location set map_id = '4J' where id = 23;
update location set map_id = '4E' where id = 1;
update location set map_id = '4E' where id = 1;
update location set map_id = '5/6D' where id = 7;
update location set map_id = '3/4D' where id = 6;
update location set map_id = '3E' where id = 2;
update location set map_id = '3W' where id = 14;
update location set map_id = '3J' where id = 9;
update location set map_id = '7W' where id = 20;
-- @formatter:on
