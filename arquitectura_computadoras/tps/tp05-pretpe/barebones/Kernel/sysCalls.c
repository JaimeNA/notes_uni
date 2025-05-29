#include <sysCalls.h>
#include <stdarg.h>
#include <keyboard.h>

#define COLOR_WHITE 0xFFFFFFFF
#define COLOR_AMBER 0x00FFBF00

int write(int fd, char * buff, int length) {
    switch (fd) {
        case 1:
            printText(buff, length, COLOR_WHITE);
            break;

        case 2:
            printText(buff, length, COLOR_AMBER);
            break;
    };

    return length;
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

uint64_t time_ticks() {
    return ticks_elapsed();
}

void sys_clear() {
    clearBuffer();
}

void sys_draw() {
    drawScreen();
}

void sys_drawSquare(uint64_t x, uint64_t y, uint64_t size, uint32_t hexColor) {
    drawSquare(x, y, size, 0xFF);   // TODO: Fix arguments
}

uint64_t sysCallDispatcher(uint64_t rax, ...) {
    va_list args;
    va_start(args, rax);  

    uint64_t ret_val = 0;

    switch(rax) {
        case 0:
            int fd = va_arg(args, int);
            const char* buff = va_arg(args, const char*);
            int length = va_arg(args, int);

            ret_val = write(fd, buff, length);
            break;
        case 1:
            ret_val = read(va_arg(args, int), va_arg(args, char*), va_arg(args, int));
            break;
        case 2:
            ret_val = ticks_elapsed();
            break;
        case 3:
            sys_clear();
            break;
        case 4:
            sys_draw();
            break;
        case 5:
            uint64_t x = va_arg(args, int);
            uint64_t y = va_arg(args, int);
            uint64_t size = va_arg(args, int);

            sys_drawSquare(x, y, size, va_arg(args, int));
            break;
        default:
            // Manejar  
            break;
    };

    va_end(args);

    return ret_val;
}