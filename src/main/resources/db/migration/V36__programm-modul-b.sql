ALTER TABLE verein_programm
    ADD COLUMN modul_b_pa BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE verein_programm
    ADD COLUMN modul_b_egitarre BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE verein_programm
    ADD COLUMN modul_b_ebass BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE verein_programm
    ADD COLUMN modul_b_keyboard BIT(1) NOT NULL DEFAULT 0;
ALTER TABLE verein_programm
    ADD COLUMN modul_b_gesang BIT(1) NOT NULL DEFAULT 0;

ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_a_1 TO modul_g_kat_a_1;
ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_a_2 TO modul_g_kat_a_2;
ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_a_titel_1_id TO modul_g_kat_a_titel_1_id;
ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_a_titel_2_id TO modul_g_kat_a_titel_2_id;
ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_b_titel_id TO modul_g_kat_b_titel_id;
ALTER TABLE verein_programm RENAME COLUMN tambouren_kat_c_titel_id TO modul_g_kat_c_titel_id;
