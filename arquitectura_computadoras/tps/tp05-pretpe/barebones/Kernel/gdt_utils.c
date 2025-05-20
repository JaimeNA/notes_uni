#include<gdt_utils.h>

static uint8_t * const IDT_START = (uint8_t*)0x1000;
static uint8_t * const IDT_END = (uint8_t*)0x1FFF;

static uint8_t const DESCRIPTOR_SIZE = 16;	// 16 Bytes

#pragma pack(push)		/* Push de la alineación actual */
#pragma pack (1) 		/* Alinear las siguiente estructuras a 1 byte */

typedef struct {
    unsigned short limit_low;
    unsigned short base_low;
    unsigned char base_middle;
    unsigned char access;
    unsigned char granularity;
    unsigned char base_high;
} DESC_GDT;
#pragma pack(pop)		/* Reestablece la alinceación actual */

DESC_GDT * gdt = (DESC_GDT *) IDT_START;	// IDT de 255 entradas


static void setup_IDT_entry (int index, uint64_t offset);

void load_gdt() {
    setup_GDT_entry(0, 0, 0, 0, 0);	// Kernel Space
    setup_GDT_entry(1, 0, 0xFFFFFF, 0b10011110, 0xCF);	// Kernel Space
    //setup_GDT_entry(2, 0x10000, 0xFFFFFFFF, 0b11111110, 0xCF);	// User Space
}

static void setup_GDT_entry (int index, unsigned long base, unsigned long limit, unsigned char access, unsigned char gran) {
  gdt[index].limit_low = (limit & 0xFFFF);
  gdt[index].base_low = (base & 0xFFFF);
  gdt[index].base_middle = (base >> 16) & 0xFF;
  gdt[index].access = access;
  gdt[index].granularity = ((limit >> 16) & 0x0F);
  gdt[index].base_high = (base >> 24) & 0xFF;


  gdt[index].granularity |= (gran & 0xF0);
}


// void printDescripor(int i) {
// 	ccNewline();
// 		ccPrint("	- N", 0x0F);
// 		ccPrintDec(i, 0x0F);

// 		ccNewline();
// 		ccPrint("		+  OFFSET_1: ", 0x0F);
// 		ccPrintHex(get_idt_descriptor(i).offset_1, 0x0F);
// 		ccNewline();
// 		ccPrint("		+  SELECTOR: ", 0x0F);
// 		ccPrintHex(get_idt_descriptor(i).selector, 0x0F);
// 		ccNewline();
// 		ccPrint("		+  IST: ", 0x0F);
// 		ccPrintHex(get_idt_descriptor(i).ist, 0x0F);
// 		ccNewline();
// 		ccPrint("		+  TYPE_ATTRIBUTES ", 0x0F);
// 		ccPrintBin(get_idt_descriptor(i).type_attributes, 0x0F);
// 		ccNewline();
// 		ccPrint("		+  OFFSET_2: ", 0x0F);
// 		ccPrintHex(get_idt_descriptor(i).offset_2, 0x0F);
// 		ccNewline();
// 		ccPrint("		+  OFFSET_3: ", 0x0F);
// 		ccPrintHex(get_idt_descriptor(i).offset_3, 0x0F);
// 		ccNewline();
// }
