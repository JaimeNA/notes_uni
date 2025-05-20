#ifndef IDT_UTILS_H
#define IDT_UTILS_H

#include <stdint.h>

static void setup_GDT_entry(int index, unsigned long base, unsigned long limit, unsigned char access, unsigned char gran);
void load_gdt();

#endif