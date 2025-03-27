nasm -f elf bugged.asm
nasm -f elf libasm.asm

ld -m elf_i386 bugged.o libasm.o -o out

./out

