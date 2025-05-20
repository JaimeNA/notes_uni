#ifndef SYSCALLS_H
#define SYSCALLS_H

#include <stdint.h>

int write(int fd, char * buff, int length);

int read(int fd, char * buff, int length);

void sysCallDispatcher(uint64_t rax, ...);

#endif