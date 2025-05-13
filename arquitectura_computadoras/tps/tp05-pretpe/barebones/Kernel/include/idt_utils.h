#ifndef IDT_UTILS_H
#define IDT_UTILS_H

#include <stdint.h>

#include <colorConsole.h>

static uint8_t * const IDT_START = (uint8_t*)0x0000;
static uint8_t * const IDT_END = (uint8_t*)0xFFFF;

static uint8_t const DESCRIPTOR_SIZE = 16;	// 16 Bytes

struct InterruptDescriptor64 {
	uint16_t offset_1;        // offset bits 0..15
   	uint16_t selector;        // a code segment selector in GDT or LDT
   	uint8_t  ist;             // bits 0..2 holds Interrupt Stack Table offset, rest of bits zero.
   	uint8_t  type_attributes; // gate type, dpl, and p fields
   	uint16_t offset_2;        // offset bits 16..31
   	uint32_t offset_3;        // offset bits 32..63
   	uint32_t zero;            // reserved
};

struct InterruptDescriptor64 get_idt_descriptor(uint8_t index);
void set_idt_descriptor(uint8_t index, struct InterruptDescriptor64 descriptor);


void printDescripor(int i);

#endif