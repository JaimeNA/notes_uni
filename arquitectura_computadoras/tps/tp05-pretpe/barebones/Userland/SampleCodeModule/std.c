#include<std.h>

#define NO_ARG 0x0

uint64_t write(int fd, char * buff, int length) {
    return syscallWizard(0, fd, buff, length);
}

uint64_t read(int fd, char * buff, int length) {
    return syscallWizard(1, fd, buff, length);
}

uint64_t time_ticks(){
    return syscallWizard(2, NO_ARG, NO_ARG, NO_ARG);
}

void sys_clear() {
    return syscallWizard(3, NO_ARG, NO_ARG, NO_ARG);
}

void sys_draw() {
    return syscallWizard(4, NO_ARG, NO_ARG, NO_ARG);
}

void sys_drawSquare(uint64_t x, uint64_t y, uint64_t size, uint32_t hexColor) {
    return syscallWizard(5, x, y, size);
}

int strLen(char* str) {
	int count = 0;
	while (str[count++] != 0);

	return count;
}
