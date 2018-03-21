#!/bin/bash

#docker run -v /var/lib/docker/volumes/jenkins-data/_data/workspace/demo-3_develop-PCXMIVKOQCVF5ZGT4TOCQXASPPPMZQZWMEO2JUUU4OGUCK6ZVN4Q/postman-collection:/etc/newman  \
#postman/newman_ubuntu1404  run "demo-api.json.postman_collection"  \
#--disable-unicode  --environment="test.json.postman_environment" \
#--reporters="junit,cli"

docker run -t postman/newman_ubuntu1404 run "https://www.getpostman.com/collections/8a0c9bc08f062d12dcda"