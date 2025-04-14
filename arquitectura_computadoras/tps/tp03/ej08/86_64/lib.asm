GLOBAL kill
GLOBAL read
GLOBAL write
GLOBAL open
GLOBAL close
GLOBAL strlen

ALIGN 4

; NOTE
;
; Since this is 86_64 assembly, not the parameters go in the following order(for INTEGERS)
; rdi, rsi, rdx, rcx, r8 and r9

; ------ Closes program with pid passed through stack ------
kill:
	push rbp
	mov rbp, rsp

	; INTEGER type argument, already on rdi
	mov rax, 62		; Kill code
	mov rsi, 9		; Send kill signal
	syscall

	leave
	ret

; ------ Reads a file descriptor, read the number of bytes specified and returns it on the pointer ----
read:
	push rbp
	mov rbp, rsp

	mov rax, 0
	; File descriptor
	; Buffer pointer
	; Buffer size, already on rdx
	syscall

	leave
	ret

; ------ Recieves string address and file decriptor via stack and writes it returns number of bytes written ------
write:
	push rbp
	mov rbp, rsp

	; File descriptor, already on rdi
	; buffer, already on rsi
	; length, already on rdx

	mov rax, 0x1
	syscall				; Call write

	mov rsp, rbp
	pop rbp
	ret

; ------ Open file specified and returns file descriptor ------
open:
	push rbp
	mov rbp, rsp
	
	mov rax, 2
	; File path
	mov rsi, 2			; Flag, RDWR
	mov rdx, 0			; Mode
	syscall

	leave
	ret

; ------ Close file specified and returns 0 on success and -1 on error ------
close:
	push rbp
	mov rbp, rsp
	
	mov eax, 3
	; File descriptor already in rdi
	syscall

	leave
	ret
; ------ Recieves string address on eax and returns length on eax ------
strlen:
	push rbp
	mov rbp, rsp

	push rbx 

	mov rax, rdi

	mov rbx, 0h
	mov rcx, 0h		; Accumulator

.loop:
	mov bl, [rax]
	cmp	bl, 0h
	jz .end
	inc rax
	inc rcx
	jmp .loop

.end:
	mov rax, rcx

	pop rbx

	mov rsp, rbp
	pop rbp
	ret
