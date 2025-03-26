section .text
GLOBAL _start

; Library functions
EXTERN print
EXTERN exit

_start:
	mov ebx, 10		; Seconds to sleep
	mov eax, 1bh
	int 80h			; Set alarm

	mov eax, 1dh
	int 80h			; Pause until alarm sends signal
