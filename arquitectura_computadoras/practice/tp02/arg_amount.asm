section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ebx, string_to_print
	mov ebp, esp
	mov edx, [ebp]
	call num_to_string
	call exit

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

	mov dword[ebx], '\n'
	mov dword[ebx+1], 0h
	popad
	call print
	ret

section .data
	string_to_print db 15h; Define string

