#ifndef SYSCALLS_H
#define SYSCALLS_H

#include <stdint.h>
#include <videoDriver.h>
#include <time.h>

int write(int fd, char * buff, int length);
int read(int fd, char * buff, int length);

void sys_clear();
void sys_draw();
void sys_drawSquare(uint64_t x, uint64_t y, uint64_t size, uint32_t hexColor);


/* Returns current time in number of ticks since start */
uint64_t time_ticks();

uint64_t sysCallDispatcher(uint64_t rax, ...);

#endif