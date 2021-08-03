# quiz-fest Project

first start docker and wait for the engine to start
then run docker compose with
docker-compose -f "C:\Users\jobernha\Desktop\swa\quiz-fest\src\main\docker\docker-compose.yaml" up
or
docker-compose up
in the docker directory

when keycloak and postgresql are fully started you can run
.\mvnw clean compile quarku:dev
or
.\mvnw test

users examples configured in keykloak
    Username, Password
 - jobernhard, AB1234
 - laupeter, laupeter
 - jdoe, jdoe
 - category-admin, admin (with role to edit category)