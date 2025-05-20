GLOBAL read 
GLOBAL write

section .text
	
; int read(int fd, char * buff, int length);
read:
	push rbp
	mov rbp, rsp

    mov rax, 0
    int 80h     ; Not properly setup, for testing only

	mov rsp, rbp
	pop rbp
	ret

; int write(int fd, char * buff, int length);
write:
	push rbp
	mov rbp, rsp

    mov rax, 0
    int 80h     ; Not properly setup, for testing only

	mov rsp, rbp
	pop rbp
	ret


