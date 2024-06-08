alter table verein_programm
    drop column bonus;

alter table verein_programm
    add column modul_g_kat_a_bonus numeric(3, 1) null;

alter table verein_programm
    add column modul_g_kat_b_bonus numeric(3, 1) null;

alter table verein_programm
    add column modul_g_kat_c_bonus numeric(3, 1) null;
