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

 with path q/dokumentation openapi swagger dokumentation can be found to use ist a token needs to be aqured from keycloak
 //TODO postman curl post example token
 the aqured token needs to be usesd with swagger in oder to work propperly