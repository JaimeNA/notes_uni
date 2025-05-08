#!/bin/bash

NAME="tp05-pretpe"

docker start $NAME
docker exec -it $NAME make clean -C /root/x64BareBones/Toolchain
docker exec -it $NAME make clean -C /root/x64BareBones
docker exec -it $NAME make -C /root/x64BareBones/Toolchain
docker exec -it $NAME make -C /root/x64BareBones
#docker stop $NAME
