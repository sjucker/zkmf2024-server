ALTER TABLE user ADD COLUMN created_at datetime NULL;
ALTER TABLE user ADD COLUMN email_verification varchar(255) NULL;
ALTER TABLE user ADD COLUMN email_verified_at datetime NULL;
