#!/bin/bash


echo "stoping container $1"
docker stop $1

echo "rm container $1"
docker rm $1

#Compartir la cache para que no baje las dependencias todo el tiempo
echo "running db migrations"
docker run --rm -v "$WORKSPACE":/home/gradle/project -w /home/gradle/project gradle:4.6.0-jdk8-alpine gradle flywayMigrate

echo "running container demo-api with image $1:$2"
docker run --rm --name $1 -d -p 9090:8080 $1:$2