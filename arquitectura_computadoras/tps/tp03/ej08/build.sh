#!/bin/bash

nasm -f elf32 lib.asm
gcc -c -m32 -fno-builtin main.c
gcc -m32 main.o lib.o -o main
