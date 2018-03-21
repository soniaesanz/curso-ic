#!/bin/bash

#docker run -v /var/lib/docker/volumes/jenkins-data/_data/workspace/demo-3_develop-PCXMIVKOQCVF5ZGT4TOCQXASPPPMZQZWMEO2JUUU4OGUCK6ZVN4Q/postman-collection:/etc/newman  \
#postman/newman_ubuntu1404  run "demo-api.json.postman_collection"  \
#--disable-unicode  --environment="test.json.postman_environment" \
#--reporters="junit,cli"

run "demo-api.json.postman_collection"  \
 --disable-unicode  --environment="test.json.postman_environment" \
 reporters="junit,cli"