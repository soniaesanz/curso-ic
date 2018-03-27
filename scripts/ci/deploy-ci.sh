#!/bin/bash


echo "stoping container $1"
docker stop $1

echo "rm container $1"
docker rm $1

#Los nombres de los archivos de configuracion
echo "running db migrations"
docker run --rm -v $WORKSPACE/src/main/resources/db/migration:/flyway/sql -v /root/flyway/$1/ic:/flyway/conf -v /root/flyway/jars:/flyway/drivers boxfuse/flyway migrate

echo "running container demo-api with image $1:$2"
docker run --rm --name $1 -d -p 9090:8080 $1:$2