nasm -f elf sys_read.asm
nasm -f elf libasm.asm

ld -m elf_i386 sys_read.o libasm.o -o out

./out

