GLOBAL toUpper

; ------ Pasa a mayuscula la primer letra de cada palabra ------
toUpper:
	push ebp
	mov ebp, esp
	push ebx

	mov ebx, [ebp+8]

	mov ecx, 0h			; Contador de palabras
	mov ah, 0h			; Flag, 1=ya se paso a mayuscula la primer letra; 0=caso contrario

.loop:
	cmp byte[ebx], 0h	; Si llego al final, salir de la funcion
	je .end
	cmp byte[ebx], ' '	; Si es un espacio, resetar el flag
	je .white_space
	cmp byte[ebx], 'A'	; Continuar a siguiente caracter si no es letra
	jl .continue
	cmp byte[ebx], 'z'
	jg .continue

	; Si llego hasta aca entonces es una letra
	cmp ah, 0h			; Si es la primer letra, pasarla a mayuscula
	jne .continue	
	mov al, [ebx]
	add al, 'A'-'a'
	mov byte[ebx], al
	mov ah, 1h			; Setear flag 
	jmp .continue

.white_space:
	mov ah, 0h			; Flag en 0 para que la proxima letra sea pasada a mayuscula

.continue:
	inc ecx
	inc ebx
	jmp .loop

.end:
	mov eax, ecx		
	pop ebx
	leave
	ret

