	.file	"test.c"
	.intel_syntax noprefix
	.text
	.section	.rodata
.LC0:
	.string	"Struct: \n"
.LC1:
	.string	"a: %d, b: %c, c: %f\n"
	.text
	.globl	test
	.type	test, @function
test:
.LFB0:
	.cfi_startproc
	push	ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	mov	ebp, esp
	.cfi_def_cfa_register 5
	push	ebx
	sub	esp, 4
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	add	ebx, OFFSET FLAT:_GLOBAL_OFFSET_TABLE_
	sub	esp, 12
	lea	eax, .LC0@GOTOFF[ebx]
	push	eax
	call	printf@PLT
	add	esp, 16
	mov	eax, DWORD PTR 8[ebp]
	fld	DWORD PTR 8[eax]
	mov	eax, DWORD PTR 8[ebp]
	movzx	eax, BYTE PTR 4[eax]
	movsx	edx, al
	mov	eax, DWORD PTR 8[ebp]
	mov	eax, DWORD PTR [eax]
	sub	esp, 12
	lea	esp, -8[esp]
	fstp	QWORD PTR [esp]
	push	edx
	push	eax
	lea	eax, .LC1@GOTOFF[ebx]
	push	eax
	call	printf@PLT
	add	esp, 32
	nop
	mov	ebx, DWORD PTR -4[ebp]
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE0:
	.size	test, .-test
	.globl	main
	.type	main, @function
main:
.LFB1:
	.cfi_startproc
	lea	ecx, 4[esp]
	.cfi_def_cfa 1, 0
	and	esp, -16
	push	DWORD PTR -4[ecx]
	push	ebp
	mov	ebp, esp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	push	ecx
	.cfi_escape 0xf,0x3,0x75,0x7c,0x6
	sub	esp, 36
	call	__x86.get_pc_thunk.dx
	add	edx, OFFSET FLAT:_GLOBAL_OFFSET_TABLE_
	mov	eax, ecx
	mov	eax, DWORD PTR 4[eax]
	mov	DWORD PTR -28[ebp], eax
	mov	eax, DWORD PTR gs:20
	mov	DWORD PTR -12[ebp], eax
	xor	eax, eax
	mov	DWORD PTR -24[ebp], 14
	mov	BYTE PTR -20[ebp], 100
	fld	DWORD PTR .LC2@GOTOFF[edx]
	fstp	DWORD PTR -16[ebp]
	sub	esp, 12
	lea	eax, -24[ebp]
	push	eax
	call	test
	add	esp, 16
	mov	eax, 0
	mov	edx, DWORD PTR -12[ebp]
	sub	edx, DWORD PTR gs:20
	je	.L4
	call	__stack_chk_fail_local
.L4:
	mov	ecx, DWORD PTR -4[ebp]
	.cfi_def_cfa 1, 0
	leave
	.cfi_restore 5
	lea	esp, -4[ecx]
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE1:
	.size	main, .-main
	.section	.rodata
	.align 4
.LC2:
	.long	1102787379
	.section	.text.__x86.get_pc_thunk.dx,"axG",@progbits,__x86.get_pc_thunk.dx,comdat
	.globl	__x86.get_pc_thunk.dx
	.hidden	__x86.get_pc_thunk.dx
	.type	__x86.get_pc_thunk.dx, @function
__x86.get_pc_thunk.dx:
.LFB2:
	.cfi_startproc
	mov	edx, DWORD PTR [esp]
	ret
	.cfi_endproc
.LFE2:
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB3:
	.cfi_startproc
	mov	ebx, DWORD PTR [esp]
	ret
	.cfi_endproc
.LFE3:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (GNU) 14.2.1 20250207"
	.section	.note.GNU-stack,"",@progbits
