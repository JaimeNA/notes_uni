#!/bin/bash

nasm -f elf32 fact.asm 
nasm -f elf32 seven.asm
gcc -c -m32 -fno-builtin main.c

gcc -m32 main.o fact.o seven.o -o main

