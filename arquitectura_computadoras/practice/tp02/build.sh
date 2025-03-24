nasm -f elf arg_amount.asm
nasm -f elf libasm.asm

ld -m elf_i386 arg_amount.o libasm.o -o out

./out

