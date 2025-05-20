#ifndef KEYBOARD_H
#define KEYBOARD_H

#include <stdint.h>
#include <colorConsole.h>

extern uint8_t keyboard_status();
extern char keyboard_output();

uint8_t pollKeyboard();
void keyPress();

#endif