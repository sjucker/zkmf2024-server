alter table location
    add column map_id varchar(3);

-- @formatter:off
update location set map_id = '1' where id = 1;
update location set map_id = '2' where id = 2;
update location set map_id = '3' where id = 3;
update location set map_id = '4' where id = 4;
update location set map_id = '5' where id = 5;
update location set map_id = '6' where id = 6;
update location set map_id = '7' where id = 7;
update location set map_id = '8' where id = 8;
update location set map_id = '9' where id = 9;
update location set map_id = '10' where id = 10;
update location set map_id = '11' where id = 11;
update location set map_id = '12' where id = 12;
update location set map_id = '13' where id = 13;
update location set map_id = '14' where id = 14;
update location set map_id = '15' where id = 15;
update location set map_id = '16' where id = 16;
update location set map_id = '17' where id = 17;
update location set map_id = '18' where id = 18;
update location set map_id = '19' where id = 19;
update location set map_id = '20' where id = 20;
update location set map_id = '21' where id = 21;
update location set map_id = '22' where id = 22;
update location set map_id = '23' where id = 23;
update location set map_id = '24' where id = 24;
-- @formatter:on
