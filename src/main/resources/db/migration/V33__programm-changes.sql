DELETE
FROM titel
WHERE fk_verein IS NOT NULL;

ALTER TABLE titel
    MODIFY COLUMN modul VARCHAR(255) NOT NULL;
