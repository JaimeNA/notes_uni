GLOBAL factorial

ALIGN 4

; ------ Recieves n via stack and computes factorial recursibely ------
factorial:
	push ebp
	mov ebp, esp
	
	sub esp, 4	; For the local variable

	; If section
	mov ecx, dword[ebp+8]
	cmp ecx, 0h
	jne .continue

	mov eax, 1
	jmp .end

.continue:
	
	dec ecx
	push ecx

	call factorial
	
	pop ecx 
	
	mov ecx, dword[ebp+8]

	; Local variable
	mov dword[ebp-4], eax
	
	call mult

.end:
	mov esp, ebp
	pop ebp
	ret

; Recieves values to multiply in eax and ecx, returns in ebx
mult:
	push ebp
	mov ebp, esp
	push edx
	push ecx

	mov edx, 0

.loop:
	dec ecx
	add edx, eax
	cmp ecx, 0h
	jnz .loop

	mov eax, edx

	pop ecx
	pop edx
	mov esp, ebp
	pop ebp
	ret

	

