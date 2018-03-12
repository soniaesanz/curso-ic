# Spring boot demo

### Tecnologias requeridas

* java 8
* [Docker]
* [gradle] (es recomendable usar [sdkman])

### Para ejecutarlo

Levantar sql server 

```sh
$ docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Pa$$w0rd1 -p 1433:1433 -d microsoft/mssql-server-linux:2017-CU4
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
   