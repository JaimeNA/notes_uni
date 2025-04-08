GLOBAL seven

ALIGN 4

seven:
	push ebp
	mov ebp, esp

	mov eax, 7

	mov esp, ebp
	pop ebp
	ret

