#!/bin/bash

sleep 60s

echo "running on $WORKSPACE"

docker run -v $WORKSPACE:/etc/newman  \
postman/newman_ubuntu1404  run "postman-collection/demo-api.json.postman_collection"  \
--disable-unicode  --environment="postman-collection/test.json.postman_environment" \
--reporters="junit,cli"




#newman run "postman-collection/demo-api.json.postman_collection"  \
# --disable-unicode  --environment="postman-collection/test.json.postman_environment" \
# reporters="junit,cli"
