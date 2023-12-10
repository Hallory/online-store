spring boot init

OpenAPI docs
http://localhost:8080/v3/api-docs

Swagger docs
http://localhost:8080/swagger-ui/index.html

Generated OpenAPI docs in json report
/target/openapi.json


Prepare backend from Dockerfile and pull postgres:latest
docker compose up -d

Delete current containers
docker compose down

Delete current containers with volumes and networks
docker compose down --volumes

Print containers logs
docker compose logs

