GLOBAL checkLong

section .data
    msg: db "Hola Mundo", 0
    len: db 10

section .text

checkLong:
    push ebp
    mov ebp, esp

    push ebx

    mov eax, 0h
    mov ebx, [ebp + 8]
    mov ecx, [ebp + 12]

.loop:
    cmp byte[ebx], 0h
    je .end
    inc eax
    inc ebx
    jmp .loop

.end:
    sub eax, ecx

    pop ebx

    mov esp, ebp
    pop ebp
    ret

