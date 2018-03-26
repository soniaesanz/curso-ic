#!/bin/bash

echo "stoping container $1"
docker stop $1

echo "rm container $1"
docker rm $1

echo "running container demo-api with image $1:$2"
docker run --rm --name $1 -d -p 9090:8080 $1:$2