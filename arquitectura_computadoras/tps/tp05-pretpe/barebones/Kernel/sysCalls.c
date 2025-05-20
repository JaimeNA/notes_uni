#include <sysCalls.h>
    va_end(args);
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
    write(1, "hola", 0);
}