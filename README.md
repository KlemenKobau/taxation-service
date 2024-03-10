# Taxation service

## Requirements

This project requires:
- docker
- java 17 and maven

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

Quarkus DEV UI is then available at http://localhost:8080/q/dev/.

## Testing the application

Test the application by running:
```bash
./mvnw verify -DskipITs=false
```