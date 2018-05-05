#!/bin/bash

docker ps
for CONTAINER_ID in $(docker ps -aq); do echo docker container stop "${CONTAINER_ID}"; done

docker container ls
for CONTAINER_ID in $(docker ps -aq); do echo docker container rm "${CONTAINER_ID}"; done

docker volume ls
for VOLUME_ID in $(docker volume ls -q); do echo docker volume rm "${VOLUME_ID}"; done

docker network ls
for NETWORK_ID in $(docker network ls -q); do echo docker network rm "${NETWORK_ID}"; done

docker image ls
for IMAGE_ID in $(docker images --format "{{.ID}}"); do echo docker container rmi "${IMAGE_ID}"; done
