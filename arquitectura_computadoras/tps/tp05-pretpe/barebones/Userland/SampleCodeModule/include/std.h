#ifndef STD_H
#define STD_H

#include <stdint.h>

extern uint64_t syscallWizard(uint64_t rax, uint64_t rdi, uint64_t rsi, uint64_t rdx);

uint64_t write(int fd, char * buff, int length);
uint64_t read(int fd, char * buff, int length);
uint64_t time_ticks();
void sys_clear();
void sys_draw();
void sys_drawSquare(uint64_t x, uint64_t y, uint64_t size, uint32_t hexColor);

int strLen(char* str);

#endif