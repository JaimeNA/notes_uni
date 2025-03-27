section .bss

section .data
	Buff resb 1

section .text
	global _start

_start:
Read: 
	mov eax,3			; Specify sys_read call
	mov ebx,0			; Specify File Descriptor 0: Standard Input
	mov ecx, Buff		; Pass offset of the buffer to read to
	mov edx,1			; Tell sys_read to read one char from stdin
	int 80h				; Call sys_read

	; or fall through to test for lowercase
	cmp byte [Buff],61h ; Test input char against lowercase 'a'
	jb Write			; If below 'a' in ASCII chart, not lowercase
	cmp byte [Buff],7Ah ; Test input char against lowercase 'z'

	ja Write			; If above 'z' in ASCII chart, not lowercase
	; At this point, we have a lowercase character
	sub byte [Buff],20h ; Subtract 20h from lowercase to give
	; uppercase...
	; ...and then write out the char to stdout

Write: 
	mov eax,4			; Specify sys_write call
	mov ebx,1			; Specify File Descriptor 1: Standard output
	mov ecx,Buff		; Pass address of the character to write
	mov edx,1			; Pass number of chars to write
	int 80h				; Call sys_write...
	jmp Read			; ...then go to the beginning to get another
	; character
Exit: 
	mov eax,1			; Code for Exit Syscall
	mov ebx,0			; Return a code of zero to Linux
	int 80H				; Make kernel call to exit program

; ERRORES:
; [Buff] no existe, no hace falta pasarle el valor en buff
; byte es inecesario
