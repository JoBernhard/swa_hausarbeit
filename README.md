# quiz-fest Project

first start docker and *wait* for the engine to start
then 
## run docker compose 
with
```bash
docker-compose -f "C:\Users\jobernha\Desktop\swa\quiz-fest\src\main\docker\docker-compose.yaml" up
```
or
```bash
docker-compose up
```
in the docker directory ``.\Desktop\swa\quiz-fest\src\main\docker\``

when keycloak and postgresql are fully started __(this may take a few minutes)__
***

## run server
```bash
.\mvnw clean compile quarku:dev
```
or
## test server
```bash
.\mvnw test
```
from the ``Desktop\swa\quiz-fest`` Direktory
***

## keycloak
users examples configured in keykloak are 
|Username   | Password  | Role  |
| --------- | --------- | ----- |
|jobernhard | AB1234    |       |
|laupeter   | laupeter  |       |
|jdoe       | jdoe      |       |
|category-admin| admin  |admin  |

***

## swagger dokumentation
 with path `q/dokumentation` openapi swagger dokumentation can be found to use it a token needs to be aquired from keycloak
 
```bash 
curl --location --request POST "http://localhost:8180/auth/realms/quiz-fest/protocol/openid-connect/token"^
 --header "Content-Type: application/x-www-form-urlencoded" ^
 --header "Authorization: Basic cXVpei1mZXN0OjU3ZjI0MzE3LTJmOGItNDA1Ni05MzA4LWE3NzljMDNjNDI5MQ=="^
 --data-urlencode "username=laupeter"^
 --data-urlencode "password=laupeter"^
 --data-urlencode "grant_type=password"
```

 the aquired token needs to be usesd with swagger in oder to work propperly
 ***

## Postman
 a collection of postman tests is provided at
 ``.\quiz-fest.postman_collection.json``
 This can be Imported into Postman
 if you use this collection first run the "Generate Accestoken" so the access token is set for the other tests

## Finish
 when your done stop the quarkus server with ``Strg + C``
 and run 
 ```bash
 docker compose down
 ```
  to delete the containers