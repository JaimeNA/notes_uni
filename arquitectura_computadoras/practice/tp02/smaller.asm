section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ecx, 4
	mov ebx, msg
	call smaller
	mov edx, eax
	call num_to_string
	call exit

; ------ Recieves array in ebx and its length in ecx and outputs the smaller one in eax ------
smaller:
	push ebx		; Save current ebx
	push ecx

	mov eax, [ebx]
loop_smaller:
	cmp eax, [ebx]
	jl next
	mov eax, [ebx]
next:
	dec ecx
	add ebx, 4		; Go to the next 4 bytes
	cmp ecx, 0
	je end_smaller
	jmp loop_smaller

end_smaller:
	pop ecx
	pop ebx
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
	msg dd 000000F1h, 00000004h, 00000002h, 00000006h ; Define string

