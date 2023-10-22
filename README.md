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
