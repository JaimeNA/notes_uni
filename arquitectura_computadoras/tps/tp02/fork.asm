section .text
GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:

	mov eax, 2h	; Fork
	int 80h
	cmp eax, 0	; For the child, eax will be 0
	je .child

.parent:
	mov ebx, parent_str
	call print
	jmp .end

.child:
	mov ebx, child_str
	call print
	jmp .end

.end:
	call exit

section .data
	parent_str db "This is the parent", 10, 0
	child_str db "This is the child", 10, 0
