# spring boot init

## general
### OpenAPI docs<br />
http://localhost:8080/v3/api-docs

### Swagger docs<br />
http://localhost:8080/swagger-ui/index.html

### Build 
1) up backend and auth 
- pull and config keycloak (for dev purpose here must be ./keycloak/realm.quickstart.json)
docker compose up -d

2) up dockerized frontend app 
- in existing network - es-backend-sample
- oauth2 client - fe-dev-dockerized

### Delete containers<br />
docker compose down

### Delete containers with volumes and networks<br />
docker compose down --volumes


## other
docker compose -f compose-standalone.yml up -d

docker compose -f compose-standalone.yml down --volumes
