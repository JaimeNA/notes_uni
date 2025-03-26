section .text
GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	push ebp
	mov ebp, esp

	mov ebx, 0h		; File descriptor for standard input
	mov ecx, buffer	; Buffer address
	mov edx, 15h	; Buffer size
	mov eax, 3h

	int 80h			; Call read

	call to_upper

	mov ebx, 1h		; File descriptor for standard output
	mov eax, 4h

	int 80h			; Call write

	mov esp, ebp
	pop ebp			; Restore stack pointer
	call exit

; ------ Reciever buffer in ecx and its length in edx ------
to_upper:
	push ebx
	push ecx
	push edx
	push eax

	mov bh, 'a'
	sub bh, 'A'

.loop:
	mov eax, [ecx]
	call lower_case
	cmp eax, 0h
	je .next
	mov bl, [ecx]
	sub bl, bh
	mov [ecx], bl
.next:
	inc ecx
	dec edx
	jnz .loop

	pop eax
	pop edx
	pop ecx
	pop ebx
	ret

; ------ Puts 1 in eax if its a lowercase letter and 0 if not ------
lower_case:
	cmp al, 'a'
	jl .not_equal
	cmp al, 'z'
	jg .not_equal
	mov eax, 1h
	jmp .end
.not_equal:
	mov eax, 0h

.end:
	ret

section .data
	buffer db 15h
	
