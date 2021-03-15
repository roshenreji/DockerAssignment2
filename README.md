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
Starting studentapplication_mysql-newdocker_1 ... done
Starting studentapplication_springboot-docker-container_1 ... done
Attaching to studentapplication_mysql-newdocker_1, studentapplication_springboot-docker-container_1
mysql-newdocker_1              | 2021-03-15 13:37:49+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.33-1debian10 started.
mysql-newdocker_1              | 2021-03-15 13:37:49+00:00 [Note] [Entrypoint]: Switching to dedicated user 'mysql'
mysql-newdocker_1              | 2021-03-15 13:37:49+00:00 [Note] [Entrypoint]: Entrypoint script for MySQL Server 5.7.33-1debian10 started.
mysql-newdocker_1              | 2021-03-15T13:37:50.596909Z 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).   
mysql-newdocker_1              | 2021-03-15T13:37:50.939899Z 0 [Note] mysqld (mysqld 5.7.33) starting as process 1 ...
mysql-newdocker_1              | 2021-03-15T13:37:50.950807Z 0 [Note] InnoDB: PUNCH HOLE support available
mysql-newdocker_1              | 2021-03-15T13:37:50.950851Z 0 [Note] InnoDB: Mutexes and rw_locks use GCC atomic builtins
mysql-newdocker_1              | 2021-03-15T13:37:50.950857Z 0 [Note] InnoDB: Uses event mutexes
mysql-newdocker_1              | 2021-03-15T13:37:50.950860Z 0 [Note] InnoDB: GCC builtin __atomic_thread_fence() is used for memory barrier
mysql-newdocker_1              | 2021-03-15T13:37:50.950862Z 0 [Note] InnoDB: Compressed tables use zlib 1.2.11
mysql-newdocker_1              | 2021-03-15T13:37:50.950864Z 0 [Note] InnoDB: Using Linux native AIO
mysql-newdocker_1              | 2021-03-15T13:37:50.952863Z 0 [Note] InnoDB: Number of pools: 1
mysql-newdocker_1              | 2021-03-15T13:37:50.953708Z 0 [Note] InnoDB: Using CPU crc32 instructions
mysql-newdocker_1              | 2021-03-15T13:37:50.960946Z 0 [Note] InnoDB: Initializing buffer pool, total size = 128M, instances = 1, chunk size = 128M
mysql-newdocker_1              | 2021-03-15T13:37:50.968794Z 0 [Note] InnoDB: Completed initialization of buffer pool
mysql-newdocker_1              | 2021-03-15T13:37:50.970725Z 0 [Note] InnoDB: If the mysqld execution user is authorized, page cleaner thread priority can be changed. See the man page of setpriority().
mysql-newdocker_1              | 2021-03-15T13:37:50.982413Z 0 [Note] InnoDB: Highest supported file format is Barracuda.
mysql-newdocker_1              | 2021-03-15T13:37:50.991477Z 0 [Note] InnoDB: Creating shared tablespace for temporary tables
mysql-newdocker_1              | 2021-03-15T13:37:50.991583Z 0 [Note] InnoDB: Setting file './ibtmp1' size to 12 MB. Physically writing the file full; Please wait ...
mysql-newdocker_1              | 2021-03-15T13:37:51.011170Z 0 [Note] InnoDB: File './ibtmp1' size is now 12 MB.
mysql-newdocker_1              | 2021-03-15T13:37:51.012178Z 0 [Note] InnoDB: 96 redo rollback segment(s) found. 96 redo rollback segment(s) are active.
mysql-newdocker_1              | 2021-03-15T13:37:51.012223Z 0 [Note] InnoDB: 32 non-redo rollback segment(s) are active.
mysql-newdocker_1              | 2021-03-15T13:37:51.013058Z 0 [Note] InnoDB: 5.7.33 started; log sequence number 12698726
mysql-newdocker_1              | 2021-03-15T13:37:51.013447Z 0 [Note] InnoDB: Loading buffer pool(s) from /var/lib/mysql/ib_buffer_pool
mysql-newdocker_1              | 2021-03-15T13:37:51.013773Z 0 [Note] Plugin 'FEDERATED' is disabled.
mysql-newdocker_1              | 2021-03-15T13:37:51.016454Z 0 [Note] InnoDB: Buffer pool(s) load completed at 210315 13:37:51
mysql-newdocker_1              | 2021-03-15T13:37:51.020562Z 0 [Note] Found ca.pem, server-cert.pem and server-key.pem in data directory. Trying to enable SSL support using them.
mysql-newdocker_1              | 2021-03-15T13:37:51.020619Z 0 [Note] Skipping generation of SSL certificates as certificate files are present in data directory.
mysql-newdocker_1              | 2021-03-15T13:37:51.021689Z 0 [Warning] CA certificate ca.pem is self signed.
mysql-newdocker_1              | 2021-03-15T13:37:51.021804Z 0 [Note] Skipping generation of RSA key pair as key files are present in data directory.
mysql-newdocker_1              | 2021-03-15T13:37:51.023114Z 0 [Note] Server hostname (bind-address): '*'; port: 3306
mysql-newdocker_1              | 2021-03-15T13:37:51.023292Z 0 [Note] IPv6 is available.
mysql-newdocker_1              | 2021-03-15T13:37:51.023465Z 0 [Note]   - '::' resolves to '::';
mysql-newdocker_1              | 2021-03-15T13:37:51.023886Z 0 [Note] Server socket created on IP: '::'.
mysql-newdocker_1              | 2021-03-15T13:37:51.134699Z 0 [Warning] Insecure configuration for --pid-file: Location '/var/run/mysqld' in the path is accessible to all OS users. Consider choosing a different directory.
mysql-newdocker_1              | 2021-03-15T13:37:51.147174Z 0 [Note] Event Scheduler: Loaded 0 events
mysql-newdocker_1              | 2021-03-15T13:37:51.147577Z 0 [Note] mysqld: ready for connections.
mysql-newdocker_1              | Version: '5.7.33'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
springboot-docker-container_1  | 
springboot-docker-container_1  |   .   ____          _            __ _ _
springboot-docker-container_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
springboot-docker-container_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
springboot-docker-container_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
springboot-docker-container_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
springboot-docker-container_1  |  =========|_|==============|___/=/_/_/_/
springboot-docker-container_1  |  :: Spring Boot ::                (v2.4.2)
springboot-docker-container_1  |
springboot-docker-container_1  | 2021-03-15 13:37:55.982  INFO 1 --- [           main] c.m.s.StudentApplication                 : Starting StudentApplication v0.0.1-SNAPSHOT using Java 1.8.0_212 on c5c0ce7effd3 with PID 1 (/studentApplication-0.0.1-SNAPSHOT.jar started by root in /)
springboot-docker-container_1  | 2021-03-15 13:37:55.987  INFO 1 --- [           main] c.m.s.StudentApplication                 : No active profile set, falling back to default profiles: default
springboot-docker-container_1  | 2021-03-15 13:37:57.544  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
springboot-docker-container_1  | 2021-03-15 13:37:57.635  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 65 ms. Found 2 JPA repository interfaces.
springboot-docker-container_1  | 2021-03-15 13:37:58.330  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8082 (http)
springboot-docker-container_1  | 2021-03-15 13:37:58.352  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
springboot-docker-container_1  | 2021-03-15 13:37:58.352  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.41]
springboot-docker-container_1  | 2021-03-15 13:37:58.439  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
springboot-docker-container_1  | 2021-03-15 13:37:58.439  INFO 1 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2362 ms
springboot-docker-container_1  | 2021-03-15 13:37:59.756  INFO 1 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
springboot-docker-container_1  | 2021-03-15 13:37:59.839  INFO 1 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.27.Final
springboot-docker-container_1  | 2021-03-15 13:37:59.992  INFO 1 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
springboot-docker-container_1  | 2021-03-15 13:38:00.110  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
springboot-docker-container_1  | 2021-03-15 13:38:00.350  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
springboot-docker-container_1  | 2021-03-15 13:38:00.392  INFO 1 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL57Dialect
springboot-docker-container_1  | 2021-03-15 13:38:03.640  INFO 1 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
springboot-docker-container_1  | 2021-03-15 13:38:03.655  INFO 1 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
springboot-docker-container_1  | 2021-03-15 13:38:22.393  WARN 1 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
springboot-docker-container_1  | 2021-03-15 13:38:27.815  INFO 1 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
springboot-docker-container_1  | 2021-03-15 13:38:42.720  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8082 (http) with context path ''
springboot-docker-container_1  | 2021-03-15 13:38:42.739  INFO 1 --- [           main] c.m.s.StudentApplication                 : Started StudentApplication in 47.29 seconds (JVM running for 51.726)
````
