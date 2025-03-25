section .text
GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	call print_usr
	call exit

; ------ Prints USER ------
print_usr:
	mov ebp, esp
	pushad

	add ebp, 4		; Skip function return address
	mov eax, [ebp]	; Store arg amount
	add ebp, 4		; Skip program path

	mov	ecx, 4
	call mult		
	add ebp, eax	; Skip arguments
	add ebp, 4		; SKip NULL bytes

.usr_loop:
	mov ecx, 4		; Compare first 4 character
	mov ebx, user
	mov edx, [ebp]
	call strcmp
	cmp eax, 1
	je .end

	add ebp, 4
	cmp ebp, 0		; Continue until null
	jnz .usr_loop

.end:
	mov ebx, [ebp]
	call print

	popad
	ret

; ------ Compare two strings by length in ecx, one in ebx and another in edx, return 1 on eax if equal and 0 if not
strcmp:
	push ecx
	push ebx
	push edx

.cmp_loop:
	mov al, [ebx]
	mov ah, [edx]
	cmp ah, al
	jne .not_equal
	inc ebx
	inc edx
	mov eax, 1
	dec ecx
	jz .end
	jmp .cmp_loop

.not_equal:
	mov eax, 0h

.end:
	pop edx
	pop ebx
	pop ecx
	ret

; ------ Mult eax by ecx, returns in eax
mult:
	push ecx
	push ebx
	
	mov ebx, eax
	mov eax, 0

.mult_loop:
	add eax, ebx
	dec ecx
	jnz .mult_loop

	pop ebx
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
	string_to_print db 15h; Define string
	user db "USER=", 0

