GLOBAL _start

section .text

_start:
	push ebp
	mov ebp, esp

	mov eax,0
	cpuid				; get the vendor name
	mov [vendor_str],ebx ; store the result in vendor_id
	mov [vendor_str+4],edx
	mov [vendor_str+8],ecx

	
	push 12				;message length
	push vendor_str		;message to write (msg is a pointer to the start of the string)
	push 1				;file descriptor (stdout)
	call write

	mov esp, ebp
	pop ebp

	mov	eax,1			;system call number (sys_exit)
	mov	ebx,0			;exit status 0 (if not used is 1 as set before) "echo $?" to check
	int	0x80

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

section .bss
	vendor_str resd 12
	str_len equ $-vendor_str
