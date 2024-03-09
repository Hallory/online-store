# spring boot init

## general
### OpenAPI docs<br />
http://localhost:8081/v3/api-docs

### Swagger docs<br />
http://localhost:8081/swagger-ui/index.html

### Run<br />
1) for dev purposes required auth-server realm config in ./keycloak/realm-quickstart.json 
2) pull all images and run<br />
docker compose up -d

### Refresh dependencies
- pull latest dependent images<br />
docker compose pull 

- pull backend only latest image<br />
docker compose pull backend

### Delete containers<br />
docker compose down

### Delete containers with volumes and networks<br />
docker compose down --volumes


## other<br />
docker compose -f compose-standalone.yml up -d

docker compose -f compose-standalone.yml down --volumes
