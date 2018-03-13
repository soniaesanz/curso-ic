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
$ docker run --name some-redis -d redis -p 6379:6379
```

Para ejecutar el proyecto hay que pararse en el folder root del proyecto 

```sh
$ gradle bootRun
```

   [Docker]: <https://docs.docker.com/install/linux/docker-ce/ubuntu/#os-requirements>
   [gradle]: <https://gradle.org/>
   [sdkman]: <http://sdkman.io/>
   