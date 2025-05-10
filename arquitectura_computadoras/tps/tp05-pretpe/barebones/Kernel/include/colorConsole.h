#ifndef COLOR_CONSOLE_H
#define COLOR_CONSOLE_H

#include <stdint.h>

void ccPrint(const char * string, uint8_t color);
void ccPrintChar(char character, uint8_t color);
void ccNewline();
void ccPrintDec(uint64_t value, uint8_t color);
void ccPrintHex(uint64_t value, uint8_t color);
void ccPrintBin(uint64_t value, uint8_t color);
void ccPrintBase(uint64_t value, uint32_t base, uint8_t color);
void ccClear();

#endif
