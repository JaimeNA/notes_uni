GLOBAL read 
GLOBAL write
GLOBAL syscallWizard

section .text

; uint64_t syscallWizard(uint64_t rax, uint64_t rdi, uint64_t rsi, uint64_t rdx)
syscallWizard:
	push rbp
	mov rbp, rsp

    mov rax, rdi
    mov rdi, rsi
    mov rsi, rdx
    mov rdx, rcx
    int 80h     

	mov rsp, rbp
	pop rbp
	ret

