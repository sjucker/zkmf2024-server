# ZKMF2024

## Development

### Commands

* Start DB in Docker container:  
  `docker compose -p zkmf-2024 -f src/main/docker/postgres.yml down && docker compose -p zkmf-2024 -f src/main/docker/postgres.yml up --build`

## Heroku

### Database Backup

* Staging:  
  `heroku pg:backups:capture --app zkmf2024-server-staging`  
  `heroku pg:backups:download --app zkmf2024-server-staging`
* Prod:  
  `heroku pg:backups:capture --app zkmf2024-server`  
  `heroku pg:backups:download --app zkmf2024-server`

* Restore Prod to Staging:
    * Create a backup-dump as described above
    * Find out the ID of the current backup: `heroku pg:backups --app zkmf2024-server`
    * `heroku pg:backups:restore zkmf2024-server::<BACKUP-ID> DATABASE_URL --app zkmf2024-server-staging`

* Restore locally:
    * Drop all tables
    * `pg_restore --no-owner -h localhost -U zkmf2024 -d zkmf2024 -W latest.dump`
