# To Containerize the Spring boot Project

### Step 1: Create a Dockerfile(dockerfile)

eg:-
````
FROM openjdk:8-jre-alpine
ADD target/studentApplication-0.0.1-SNAPSHOT.jar studentApplication-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "studentApplication-0.0.1-SNAPSHOT.jar"]
````

### Step 2: Build the docker file
````
docker build -t spring-demo-docker
````

### Step 3: Create a Docker Compose File(docker-compose)
````
version: '3'
services:
  mysql-newdocker:
    image: 'mysql:5.7'
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_PASSWORD=root
      - MYSQL_DATABASE=demo
    ports:
      - "3307:3306"
  springboot-docker-container:
    image: spring-demo-docker
    ports:
      - "8082:8082"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql-newdocker:3306/demo?autoReconnect=true&useSSL=false
      SPRING_DATASOURCE_USERNAME: "root"
      SPRING_DATASOURCE_PASSWORD: "root"
    build:
      context: "./"
      dockerfile: "dockerfile"
    depends_on:
      - mysql-newdocker
````

### Step 4: Make Changes to your application.properties

````
server:
  servlet:
    context-path: /demo

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://studentapplication_mysql-newdocker_1:3306/demo
spring.datasource.username=root
spring.datasource.password=root
server.port=8082
jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
        ddl-auto: update
````

##### Note: Give the mysql container name mentioned under your project name in Docker Desktop

### Step 5: Run the docker-compose file
````
docker-compose -f docker-compose.yml up
````

##### Output
````
