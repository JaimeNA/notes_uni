section .text

GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ecx, 4
	mov ebx, msg
	call sort
	call print_array
	call exit

; ------ Recieves array in ebx and its length in ecx, sorts the array. Uses selection sort ------
sort:
	pushad

loop_sort:
	call smaller	; Returns smallest one addr on eax
    mov edx, [ebx]	; Swap with the first and increase the array pointer
	push edx		; Use stack because not enough registers
	mov edx, [eax]
	mov [ebx], edx
	pop edx
	mov [eax], edx
	add ebx, 4		; Working with 4 bytes integers
	dec ecx
	cmp ecx, 1h
	jg loop_sort	

	popad
	ret

; ------ Recieves array in ebx and its length in ecx and outputs the smallest address on eax ------
smaller:
	push ebx		; save registers
	push ecx
	push edx

	mov eax, [ebx]
	mov edx, ebx
loop_smaller:
	cmp eax, [ebx]
	jl next
	mov eax, [ebx]
	mov edx, ebx
next:
	dec ecx
	add ebx, 4		; Go to the next 4 bytes
	cmp ecx, 0
	je end_smaller
	jmp loop_smaller

end_smaller:
	mov eax, edx
	pop edx
	pop ecx
	pop ebx
	ret

; ------ Prints array on ebx with length ecx ------

print_array:
	pushad
	mov eax, ebx
	mov ebx, string_to_print

loop_array:
	mov edx, [eax]
	call num_to_string
	dec ecx
	add eax, 4
	cmp ecx, 0h
	jne loop_array

	popad
	ret

; ------ Recieves number in edx and return the string in [ebx] ------
num_to_string:		; TODO: Implement a version that passes the string via Stack
	pushad	  		; Backup registros
	mov eax, edx 	
	mov cl, 0h
	mov ebp, 10		

loop:	
	mov edx, 0h		; Initilizo en 0 para la division
	cmp al, 0
	je end
	div ebp			; Divido por 10 
	add dl, '0'		; Paso a ASCII
	push edx		; Voy pusheando al stack
	inc cl
	jmp loop

end:	
	pop eax			; Uso eax
	mov [ebx], al
	inc ebx
	dec cl
	cmp cl, 0h
	jne end

	mov dword[ebx], '\n'
	mov dword[ebx+1], 0h
	popad
	call print
	ret

section .data
	msg dd 000000F1h, 00000004h, 00000003h, 00000001h 
	string_to_print db 15h; Define string

