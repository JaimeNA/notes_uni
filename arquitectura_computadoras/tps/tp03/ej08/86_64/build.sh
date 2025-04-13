#!/bin/bash

nasm -f elf64 lib.asm
gcc -c -m64 -fno-builtin main.c
gcc -m64 main.o lib.o -o main
