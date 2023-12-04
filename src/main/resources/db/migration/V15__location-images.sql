alter table location
    add column cloudflare_id varchar(255);
alter table location
    add column kuula_id varchar(255);

-- @formatter:off
update location set cloudflare_id = '0e6455f2-ec9a-45cc-79fe-ad062b0dbd00', kuula_id = '5ZfQh'  where location.id = 14;
update location set cloudflare_id = '82172b4e-af46-4b34-0a67-30494b161c00', kuula_id = '5ZfzW' where location.id = 17;
update location set cloudflare_id = '68f436aa-a3cf-42c2-fce8-e355110daf00', kuula_id = '5ZfQq' where location.id = 18;
update location set cloudflare_id = '6458b379-2a58-41ce-8f58-abbc73b98700', kuula_id = '5c8Mp' where location.id = 19;
-- @formatter:on
