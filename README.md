# spring boot init

## general
### OpenAPI docs<br />
http://localhost:8081/v3/api-docs

### Swagger docs<br />
http://localhost:8081/swagger-ui/index.html

### Run<br />
- pull all images and run
docker compose up -d 

- pull latest dependent images
docker compose pull 

- pull backend only latest image
docker compose pull backend

### Delete containers<br />
docker compose down

### Delete containers with volumes and networks<br />
docker compose down --volumes


## other
docker compose -f compose-standalone.yml up -d

docker compose -f compose-standalone.yml down --volumes
