section .text
GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	call print_args
	call exit

; ----- Prints program arguments information ------
print_args:
	push ecx
	push eax

	mov ebp, esp	; Get stack frame
    add ebp, 4		; Skip this function return address	moc ebx, cant_arg
	
	mov ebx, cant_arg	; Print "Cantidad de argumentos: x"
	call print
	mov ebx, string_to_print
	mov edx, [ebp]
	mov ecx, edx	; Save for later use in the loop
	call num_to_string

	add ebp, 4		; Skip program path

print_args_loop:
	mov ebx, arg	; Print "argv[x]: ..."
	call print
	add ebp, 4		; Go to next
	mov ebx, [ebp]
	call print
	mov ebx, string_to_print
	mov al, ']'
	mov [ebx], al
	mov dword[ebx+1], 10
	call print
	dec ecx
	cmp ecx, 0h
	jnz print_args_loop

	pop eax
	pop ecx
	ret

; ------ Recieves null terminated string en ebx, returns length in eax ------
strlen:
	push ebx
	push ecx
	mov eax, 0h

strlen_loop:
	inc eax
	inc ebx
	mov cl, [ebx]
	cmp cl, 0h
	jnz strlen_loop

	pop ecx
	pop ebx
	ret

; ------ Recieves number in edx and return the string in [ebx] ------
num_to_string:		; TODO: Implement a version that passes the string via Stack
	pushad	  		; Backup registros
	mov eax, edx 	
	mov cl, 0h
	mov ebp, 10		

loop:	
	mov edx, 0h		; Initilizo en 0 para la division
	cmp al, 0
	je end
	div ebp			; Divido por 10 
	add dl, '0'		; Paso a ASCII
	push edx		; Voy pusheando al stack
	inc cl
	jmp loop

end:	
	pop eax			; Uso eax
	mov [ebx], al
	inc ebx
	dec cl
	cmp cl, 0h
	jne end

	mov dword[ebx], 10
	mov dword[ebx+1], 0h
	popad
	call print
	ret

section .data
	cant_arg db "Cantidad de argumentos: ", 0
	arg db "argv[", 0
	string_to_print db 15h; Define string

