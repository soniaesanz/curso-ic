#!/bin/bash

sleep 60s

docker run --rm -v $WORKSPACE/postman-collection:/etc/newman  \
                    postman/newman_ubuntu1404  run "demo-api.json.postman_collection"  \
                    --disable-unicode  --environment="test.json.postman_environment" \
                    --reporters="junit,cli"


