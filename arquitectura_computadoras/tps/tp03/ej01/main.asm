GLOBAL main
EXTERN printf

section .rodata
fmt db "Cantidad de argumentos: %d\n", 0
fmt2 db "%s\n", 0

section .text
main:
	push ebp ;Armado de stack frame
	mov ebp, esp ;

	push dword [ebp+8]
	push fmt
	call printf
	add esp, 2*4

	mov ebx, ebp
	add ebx, 8		; Skip return address and ebp
	mov esi, dword[ebx]	; Store number or arguments
	add ebx, 4		; Go to array address
	mov ebx, [ebx]	; Go to the first element of array

.loop:
	push dword[ebx]
	push fmt2
	call printf		; Print first argument
	add esp, 2*4
	add ebx, 4h
	dec esi
	cmp esi, 0h
	jg .loop

	mov eax, 0

	mov esp, ebp ;Desarmado de stack frame
	pop ebp ;
	ret
