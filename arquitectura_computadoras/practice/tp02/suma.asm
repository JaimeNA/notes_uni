section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
		mov edx, 12
		mov ebx, msg
		call sum_of_n
		call num_to_string
		call print
		call exit

; Recieves number in edx
sum_of_n:
		mov eax, 0h		; Inicia en 0	
sum:	add eax, edx
		dec edx
		cmp edx, 0
		jne sum

		mov edx, eax
		ret

num_to_string:
		pushad	  		; Backup registros
		mov eax, edx 	
		mov cl, 0h
		mov ebp, 10		

loop:	mov edx, 0h		; Initilizo en 0 para la division
		cmp al, 0
		jz end
		div ebp			; Divido por 10 
		add dl, '0'		; Paso a ASCII
		push edx		; Voy pusheando al stack
		inc cl
		jmp loop

end:	pop eax			; Uso eax
		mov [ebx], al
		inc ebx
		dec cl
		cmp cl, 0h
		jne end

		mov dword[ebx+1], 0h
		popad
		ret

section .data
		msg db 15h ; Define string
