alter table location
    add column sort_order int not null default 0;

-- @formatter:off
update location set sort_order = 1 where id = 19;
update location set sort_order = 2 where id = 18;
update location set sort_order = 3 where id = 16;
update location set sort_order = 4 where id = 17;
update location set sort_order = 5 where id = 15;
update location set sort_order = 6 where id = 14;
update location set sort_order = 7 where id = 20;
update location set sort_order = 8 where id = 22;
update location set sort_order = 9 where id = 21;
-- @formatter:on
