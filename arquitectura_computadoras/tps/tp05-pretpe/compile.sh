#!/bin/bash

NAME="tp05-pretpe"

docker start $NAME
docker exec -u $USER -it $NAME make clean -C /root/barebones/Toolchain
docker exec -u $USER -it $NAME make clean -C /root/barebones
docker exec -u $USER -it $NAME make -C /root/barebones/Toolchain
docker exec -u $USER -it $NAME make -C /root/barebones
#docker stop $NAME
