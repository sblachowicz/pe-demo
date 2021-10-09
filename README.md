# Employee CRUD Demo project
Project is using in-memory H2 database so no external database is needed. Obviously data is lost when you stop the server.

# Run project
To run the project locally simply clone repo and run:
```
./gradlew bootRun
```
To build and run the project execute commands:
```
./gradlew bootJar
```
This will build bootable .jar file. File is located in ``build/libs`` dir. Navigate to this directory and execute command:
```
java -jar employee-crud-0.0.1-SNAPSHOT.jar
```
Project binds to port ``8088``

Database is fed with some sample data (resources/employees.json) just before it is ready to accept connections.

### Docker
You can also run the project in a container. In order to do that follow the steps:
1. Build executable .jar:
```
./gradlew bootJar
```
2. Create docker image:
```
docker build --rm -t employeecrud:latest .
```
3. Deploy docker image
```
docker run --rm -d -p 8088:8088 employeecrud:latest
```

# API
There is a Swagger UI listing all the endpoints:
```
localhost:8088/swagger-ui.html
```