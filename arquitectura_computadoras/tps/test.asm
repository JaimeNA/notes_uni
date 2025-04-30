GLOBAL _start

_start:

	mov ah, 48h
	mov ebx, B8000h
	mov byte[ebx], ah
