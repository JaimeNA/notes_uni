GLOBAL imprime_pantalla

imprime_pantalla:
	push ebp
	mov ebp, esp
	push ebx

	mov ebx, A0000000h	; Top-left corner

	; *Prints header* 
	; ...

	; *Prints footer*

	mov esi, [ebp+16]	; Footer
	mov ecx, [ebp+20]	; Footer length

	add ebx, 80*5		; Last column
.loop: 
	mov al, byte[esi]
	mov byte[ebx], al	; Only read and copy one char at a time
	dec ecx
	inc ebx
	cmp ecx, 0h
	jne .loop

	pop ebx
	leave
	ret
