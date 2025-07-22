GLOBAL _start

section .text

_start:

	push buff
	push 1234

	call num2str
	add esp, 8

	push buff
	call str_len
	add esp, 4

	mov ecx, buff	;	Write syscall
	mov edx, eax
	mov ebx, 1
	mov eax, 4
	int 80h

	mov eax, 1h
	mov ebx, 0h
	int 80h

; void num2str(int num, char* buff)
num2str:
	push ebp
	mov ebp, esp
	
	push ebx

	mov ebx, 10			; Divisor
	mov eax, [ebp+8]	; Divider
	mov ecx, 0h			; Counter

.loop:
	mov edx, 0h
	div ebx
	push edx
	inc ecx
	cmp eax, 0h
	jne .loop

	mov ebx, [ebp+12]
.loop_str:
	pop eax				; Fetch numbers
	add eax, '0'		; Make it ASCII
	mov [ebx], al		; Number between 0 and 9, must be less than a byte
	inc ebx
	dec ecx
	cmp ecx, 0h
	jne .loop_str

	mov byte[ebx], 0h		; Null terminated

	pop ebx				; Restore ebx

	mov esp, ebp
	pop ebp
	ret

; int str_len(char* buff)
str_len:
	push ebp
	mov ebp, esp

	push ebx

	mov ebx, [esp+8]
	mov eax, 0h

.loop_len:
	cmp byte[ebx], 0h
	je .end

	inc eax
	inc ebx
	jmp .loop_len

.end:
	pop ebx

	mov esp, ebp
	pop ebp
	ret

section .bss
	buff: resb 20
