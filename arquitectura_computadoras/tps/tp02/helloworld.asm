section .text

GLOBAL _start

_start:
	mov ecx, msg
	mov edx, msg_len
	mov ebx, 1
	mov eax, 4
	int 80h
	
	mov eax, 1
	mov ebx, 0
	int 80h

section .data
	msg db "hey", 0 ; Define string
	msg_len equ $-msg
