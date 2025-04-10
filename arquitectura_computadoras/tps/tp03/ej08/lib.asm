GLOBAL kill
GLOBAL read
GLOBAL write
GLOBAL open
GLOBAL close
GLOBAL strlen

ALIGN 4

; ------ Closes program with pid passed through stack ------
kill:
	push ebp
	mov ebp, esp
	push ebx		; Save ebx

	mov eax, 37
	mov ebx, [ebp+8]
	mov ecx, 9		; Send kill signal
	int 80h

	pop ebx			; Restore ebx

	leave
	ret

; ------ Reads a file descriptor, read the number of bytes specified and returns it on the pointer ----
read:
	push ebp
	mov ebp, esp
	
	push ebx

	mov eax, 3
	mov ebx, [ebp+8]	; File descriptor
	mov ecx, [ebp+12]	; Buffer pointer
	mov edx, [ebp+16]	; Buffer size
	int 80h

	pop ebx

	leave
	ret

; ------ Recieves string address and file decriptor via stack and writes it returns number of bytes written ------
write:
	push ebp
	mov ebp, esp

	push ebx			; preservar ebx
	
	mov ebx, [ebp+8]	; File descriptor
	mov ecx, [ebp+12]	; buffer
	mov edx, [ebp+16]	; length

	mov eax, 0x4
	int 0x80			; Call write

	pop ebx

	mov esp, ebp
	pop ebp
	ret

; ------ Open file specified and returns file descriptor ------
open:
	push ebp
	mov ebp, esp
	
	push ebx

	mov eax, 5
	mov ebx, [ebp+8]	; File path
	mov ecx, 2			; Flag, RDWR
	mov edx, 0			; Mode
	int 80h

	pop ebx

	leave
	ret

; ------ Close file specified and returns 0 on success and -1 on error ------
close:
	push ebp
	mov ebp, esp
	
	push ebx

	mov eax, 6
	mov ebx, [ebp+8]	; File path	int 80h
	int 80h

	pop ebx

	leave
	ret
; ------ Recieves string address on eax and returns length on eax ------
strlen:
	push ebp
	mov ebp, esp

	push ebx 

	mov eax, [ebp+8]

	mov ebx, 0h
	mov ecx, 0h		; Accumulator

.loop:
	mov bl, [eax]
	cmp	bl, 0h
	jz .end
	inc eax
	inc ecx
	jmp .loop

.end:
	mov eax, ecx

	pop ebx

	mov esp, ebp
	pop ebp
	ret
