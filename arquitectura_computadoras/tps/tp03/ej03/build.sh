#!/bin/bash

nasm -f elf32 puts.asm
gcc -c -m32 -fno-builtin main.c

gcc -m32 main.o puts.o -o main

