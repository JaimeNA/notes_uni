nasm -f elf fork.asm
nasm -f elf libasm.asm

ld -m elf_i386 fork.o libasm.o -o out

./out

