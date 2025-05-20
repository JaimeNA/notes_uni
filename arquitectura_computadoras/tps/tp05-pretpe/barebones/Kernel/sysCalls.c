#include <sysCalls.h>
#include <stdarg.h>
#include <keyboard.h>

int write(int fd, char * buff, int length) {
    switch (fd) {
        case 1:
            for (int i = 0; i < length; i++) {
                if (buff[i] == '\n')
                    ccNewline();
                else
                    ccPrintChar(buff[i], 0x0F);
            }

            break;

        case 2:
            ccPrint(buff, 0x04);
            break;
    };

    return length;  // TODO: improve
}



int read(int fd, char * buff, int length) {
    
    int read = 0;

    switch (fd) {
        case 1:
            char current;

            while (read < length && (current = pollKeyboard()) != 0xA) {  // 0xA, Enter scancode
                
                if (current != 0)
                    buff[read++] = current;
            } 
            break;

        case 2:
            ccPrint(buff, 0x04);
            break;
    };

    return read;

}



void sysCallDispatcher(uint64_t rax, ...) {
    va_list args;
    va_start(args, rax);  

    switch(rax) {
        case 0:
            int fd = va_arg(args, int);
            const char* buff = va_arg(args, const char*);
            int length = va_arg(args, int);

            write(fd, buff, length);
            break;
        case 1:
            read(va_arg(args, int), va_arg(args, char*), va_arg(args, int));
            break;
        default:
            // Manejar  
            break;
    };

    va_end(args);
}