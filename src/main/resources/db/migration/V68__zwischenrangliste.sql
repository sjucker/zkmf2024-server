alter table ranking
    drop column day;

delete
from ranking_entry;

alter table ranking_entry
    add column day date not null;
