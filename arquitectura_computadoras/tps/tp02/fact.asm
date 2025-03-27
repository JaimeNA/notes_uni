section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ecx, 5
	mov ebx, msg
	call fact
	mov edx, eax
	call num_to_string
	call exit

; ------ Recieves n in ecx, devuelve en eax ------
fact:
	mov eax, 1h		; Inicia en 1	

loop_fact:	
	call mult
	dec ecx
	cmp ecx, 0
	jne loop_fact

	ret

; ------ Recieves n in eax and k in ecx, devuelve en eax ------
mult:
	push ecx		; Guardar estado de ecx para dejarlo como estaba
	push ebx

	mov ebx, eax
loop_mult:	
	dec ecx
	cmp ecx, 0
	je end_mult
	add eax, ebx
	jmp loop_mult

end_mult:
	pop ebx
	pop ecx
	ret

; ------ Recieves number in edx, returns result in edx ------
sum_of_n:
	mov eax, 0h		; Inicia en 0	
sum:	
	add eax, edx
	dec edx
	cmp edx, 0
	jne sum

	mov edx, eax
	ret


; ------ Recieves number in edx and return the string in [ebx] ------
num_to_string:
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
	msg db 15h ; Define string

