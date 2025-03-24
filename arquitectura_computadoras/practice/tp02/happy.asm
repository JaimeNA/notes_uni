section .text

GLOBAL _start

_start:
		mov ecx, msg
		mov edx, msg_len	
		call to_upper_case
		call print
		mov eax, 1	; Exit with code 0
		mov ebx, 0
		int 80h

; Recieves the pointer to text in ecx and the length in edx
to_upper_case:
		mov al, 'A'
		mov ah, 'a'
		sub al, ah
		
		mov ebp, ecx
		mov bl, dh	; Store length in bl
		mov bh, 0	; Use for index
ciclo:	cmp [ebp], ah
		jl not_c
		add [ebp], al
not_c:	inc ebp		
		dec bl
		jnz ciclo
		ret 

print:		
		mov ebx, 1
		mov eax, 4
		int 80h		; Interrupt, run syscall
		ret			; Important to return 

section .data
		msg db "h4ppy c0d1ng", 0 ; Define string
		msg_len equ $-msg
