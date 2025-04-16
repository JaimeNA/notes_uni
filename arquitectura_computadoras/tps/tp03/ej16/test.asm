GLOBAL _start
GLOBAL checkLong

section .text

_start:
	push msg
	call checkLong
	add esp, 4

	mov ecx, eax

	mov eax, 60
	mov ebx, ecx
	int 80h

checkLong:
	push ebp
	mov ebp, esp
	push ebx
	
	mov ecx, 0
	mov ebx, [ebp+8]	; String pointer
.loop:
	cmp byte[ebx], 0h	; Read byte at that mem location
	je .end
	inc ebx
	inc ecx
	jmp .loop
.end:
	mov eax, 0h
	mov al, byte[ebx+1]
	sub cl, al
	mov al, cl

	pop ebx
	leave
	ret

section .data
	msg: db "Hola Mundo", 0
	len: db 10
