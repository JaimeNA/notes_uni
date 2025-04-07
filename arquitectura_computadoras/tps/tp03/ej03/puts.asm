GLOBAL puts

ALIGN 4

; ------ Recieves string address via stack and prints it in STDOUT ------
puts:
	push ebp
	mov ebp, esp

	push ebx			; preservar ebx
	
	mov eax, [ebp+8]	; String address
	call strlen

	mov ebx, 1h			; fd
	mov ecx, [ebp+8]	; buffer
	mov edx, eax		; length

	mov eax, 0x4
	int 0x80

	pop ebx

	mov esp, ebp
	pop ebp
	ret

; ------ Recieves string address on eax and returns length on eax ------
strlen:
	push ebp
	mov ebp, esp

	push ebx 

	mov ebx, 0h
	mov ecx, 0h		; Accumulator

.loop:
	mov bl, [eax]
	cmp	bl, 0h
	jz .end
	inc eax
	inc ecx
	jmp .loop

.end:
	mov eax, ecx

	pop ebx

	mov esp, ebp
	pop ebp
	ret

	
