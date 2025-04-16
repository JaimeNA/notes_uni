GLOBAL checkLong

section .text

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
