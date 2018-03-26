#!/bin/bash

docker run --rm -v $WORKSPACE/integration-test:/etc/newman  \
                    postman/newman_ubuntu1404  run "demo-api.json.postman_collection"  \
                    --disable-unicode  --environment="test.json.postman_environment" \
                    --reporters="junit,cli"


