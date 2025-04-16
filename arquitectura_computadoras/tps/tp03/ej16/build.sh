#!/bin/bash

nasm -f elf32 checklong.asm
gcc -c -m32 -fno-builtin principal.c
gcc -m32 principal.o checklong.o -o main
