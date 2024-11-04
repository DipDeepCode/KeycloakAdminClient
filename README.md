# Demo of using `keycloak-admin-client`

## Dependency
```xml
<dependency>
    <groupId>org.keycloak</groupId>
    <artifactId>keycloak-admin-client</artifactId>
    <version>26.0.2</version>
</dependency>
```

## Keycloak-26.0.5 instance
https://github.com/keycloak/keycloak/releases/download/26.0.5/keycloak-26.0.5.zip

## Run Keycloak
1. Execute Keycloak `bin\kc.bat start-dev`
2. Go to `http://localhost:8080`
3. Create a temporary administrative user: `admin` with password `pass`
4. Create realm `TestRealm`
5. Create user `test_user` with password `test_pass`
6. Add roles `view-users` and `manage-users` to `test_user`

## Run fakeSMTP
```
java -jar fakeSMTP-2.0.jar
```

## Run app
```
mvn spring-boot:run
```

## Usage
- Create user:
    ```http request
    POST http://localhost:8081/users
    Content-Type: application/json
    
    {
      "username": "username",
      "password": "pass",
      "email": "username@foo.bar",
      "firstName": "fn",
      "lastName": "ln"
    }
    ```
- Activate user (userId from email):
    ```http request
    POST http://localhost:8081/users/{{userId}}/activate
    Content-Type: application/x-www-form-urlencoded
    ```
