alter table verein_programm
    add column modul_d_titel_selection varchar(255) null;

update verein_programm
set modul_d_titel_selection = 'TITEL_1'
where modul = 'D';
