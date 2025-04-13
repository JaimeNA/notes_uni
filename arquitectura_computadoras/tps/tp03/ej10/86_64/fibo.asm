GLOBAL fibonacci

fibonacci:
	push rbp
	mov rbp, rsp

	cmp rdi, 1
	jg .continue
	mov rax, 1
	jmp .end

.continue:
	dec rdi
	push rdi
	call fibonacci
	mov rdx, rax

	pop rdi
	dec rdi
	push rdx
	call fibonacci
	pop rdx
	add rdx, rax

	mov rax, rdx

.end:
	leave 
	ret
