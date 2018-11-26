# Spring boot demo

### Tecnologias requeridas

* java 8
* [Docker]
* [gradle] (es recomendable usar [sdkman])

### Para ejecutarlo

Levantar sql server

```sh
$ docker run -e 'ACCEPT_EULA=Y' --name sql-server -e 'SA_PASSWORD=Pa$$w0rd1' -p 1433:1433 -d microsoft/mssql-server-linux:2017-CU4
```

Cear la base de datos (también se puede hacer desde algun cliente de babse de datos)

```sh
$ docker exec -it sql-server /opt/mssql-tools/bin/sqlcmd \
   -S localhost -U SA -P 'Pa$$w0rd1' \
   -Q 'CREATE DATABASE demo'
```

Levantar redis

```sh
$ docker run --name some-redis -p 6379:6379 -d redis
```

Editar application-ic.properties, poner la ip de la máquina de cada uno (Esto es un workaround si no se posee un servidor de base de datos)

Para compilar la imagen

```sh
$ ./gradlew -DapiName=curso-ic buildImage
```

Actualizar la base de datos

```sh
$ ./gradlew flywayMigrate
```

Para levantar la imagen con el perfil ic

```sh
$ docker run --name curso -e JAVA_OPTS="-Dspring.profiles.active=ic" -p 8080:8080 -d curso-ic:0.0.1-SNAPSHOT
```

Para correr las pruebas de integración

```sh
$ ./gradlew clean verify
```

Para ejecutar el proyecto hay que pararse en el folder root del proyecto

```sh
$ ./gradlew bootRun
```

   [Docker]: <https://docs.docker.com/install/linux/docker-ce/ubuntu/#os-requirements>
   [gradle]: <https://gradle.org/>
   [sdkman]: <http://sdkman.io/>
   