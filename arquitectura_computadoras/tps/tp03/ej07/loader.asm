GLOBAL _start

EXTERN main

section .text
_start:
	call main
	mov ecx, eax	; Save main return value

	mov eax, 1
	mov ebx, ecx
	int 80h


