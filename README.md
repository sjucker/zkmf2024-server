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
    * Create and download backup-dump as described above
    * Upload `latest.dump` to a publicly accessible URL
    * `heroku pg:backups:restore 'https://<SERVER>/latest.dump' DATABASE_URL --app zkmf2024-server-staging`


* Restore locally:  
  `pg_restore --verbose --clean --no-acl --no-owner --disable-triggers -h localhost -U zkmf2024 -d zkmf2024 latest.dump`
