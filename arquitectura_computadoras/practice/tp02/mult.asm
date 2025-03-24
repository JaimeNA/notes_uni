section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ecx, 0h
	mov ch, 12
	mov cl, 4
	mov ebx, msg
	call mult
	call exit

; ------ Recieves n in ch and k in cl ------
mult:
	pushad
	mov eax, 0h		; Inicia en 0	
loop_mult:	
	add al, ch
	dec cl
	mov edx, eax
	call num_to_string
	cmp cl, 0
	jne loop_mult

	popad
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
	jz end
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
