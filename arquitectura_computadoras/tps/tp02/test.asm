section .text
    global _start

_start:

    mov dx, 0ffh
    mov bx, 20h
    add dx, bx
    push dx
    push 4
    pop cx

Ciclo:
    inc bx
    dec cx
    jnz Ciclo

    mov eax, parametros

    mov ah, [parametros]
    mov bl, [parametros+1]
    add ah, bl
    mov [salida], ah

    ret 0

section .data
parametros db 11h, 12h, 13h
salida db 0