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

Standalone postgres container
docker run -dit \
--name es-postgres-instant \
-e POSTGRES_DB=es-dev \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres-dev \
-p 5432:5432 \
-v local-es-pg-dev:/var/lib/postgresql/data \
postgres

Only backend service
docker build . && \
docker run -dit \
--name es-backend
-e POSTGRES_DB=es-dev \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres-dev \
-e POSTGRES_HOST=localhost \
-e spring.profiles.active=dev \
-p 8080:8080 \
online-store-backend
