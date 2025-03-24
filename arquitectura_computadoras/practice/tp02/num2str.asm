section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
		mov ebx, msg
		mov edx, 1209998874
		call num_to_string
		call print
		call exit

; Recieves number in ebx and outputs the number pointer in the memory location in ecx
num_to_string:
		pushad	  		; Backup registros
		mov eax, edx 	
		mov cl, 0h
		mov ebp, 10	
		

loop:	mov edx, 0h		; Initilizo en 0 para la division
		div ebp			; Divido por 10 
		cmp al, 0
		jz end
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
