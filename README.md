# spring boot init

## general
### OpenAPI docs<br />
http://localhost:8080/v3/api-docs

### Swagger docs<br />
http://localhost:8080/swagger-ui/index.html

### Build 
- backends image of Dockerfile, 
- pull postgres, 
- pull and config keycloak (for dev purpose here must be ./keycloak/realm.quickstart.json)
- !TODO real domain name for auth service<br /><br />
//docker compose up -d

### Delete containers<br />
docker compose down

### Delete containers with volumes and networks<br />
docker compose down --volumes


## other
docker compose -f compose-standalone.yml up -d

docker compose -f compose-standalone.yml down --volumes
