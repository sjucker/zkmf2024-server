ALTER TABLE user ADD COLUMN password VARCHAR(255) NOT NULL DEFAULT '{noop}pass';
ALTER TABLE user ADD COLUMN last_login datetime NULL;
