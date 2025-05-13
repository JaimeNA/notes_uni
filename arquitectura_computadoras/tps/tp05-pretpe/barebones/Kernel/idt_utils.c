#include<idt_utils.h>


// TODO: Move to its own file
struct InterruptDescriptor64 get_idt_descriptor(uint8_t index) {
	uint8_t*  desc_ptr = IDT_START + index*DESCRIPTOR_SIZE + 0x20;	// Each index is 16 bytes(128 bits)

	struct InterruptDescriptor64 toReturn;

	toReturn.offset_1 = (uint16_t) *desc_ptr;
	desc_ptr += 2;	// GOTO next 16 bits
	toReturn.selector = (uint16_t) *desc_ptr;
	desc_ptr += 2;	// GOTO next 16 bits

	toReturn.ist = (uint8_t) (*desc_ptr & 0x7);
	desc_ptr++;

	toReturn.type_attributes = (uint8_t) *desc_ptr;
	desc_ptr++;

	toReturn.offset_2 = (uint16_t) *desc_ptr;
	desc_ptr += 2;	// GOTO next 16 bits

	toReturn.offset_3 = (uint32_t) *desc_ptr;

	return toReturn;
}

void set_idt_descriptor(uint8_t index, struct InterruptDescriptor64 descriptor) {
	uint8_t*  desc_ptr = IDT_START + index*DESCRIPTOR_SIZE + 0x20;	// Each index is 16 bytes(128 bits)

	*desc_ptr = descriptor.offset_1;
	desc_ptr += 2;	// GOTO next 16 bits
	*desc_ptr = descriptor.selector;
	desc_ptr += 2;	// GOTO next 16 bits

	*desc_ptr  = descriptor.ist;
	desc_ptr++;

	*desc_ptr = descriptor.type_attributes;
	desc_ptr++;

	*desc_ptr = descriptor.offset_2;
	desc_ptr += 2;	// GOTO next 16 bits

	*desc_ptr = descriptor.offset_3;
}

void printDescripor(int i) {
	ccNewline();
		ccPrint("	- N", 0x0F);
		ccPrintDec(i, 0x0F);

		ccNewline();
		ccPrint("		+  OFFSET_1: ", 0x0F);
		ccPrintHex(get_idt_descriptor(i).offset_1, 0x0F);
		ccNewline();
		ccPrint("		+  SELECTOR: ", 0x0F);
		ccPrintHex(get_idt_descriptor(i).selector, 0x0F);
		ccNewline();
		ccPrint("		+  IST: ", 0x0F);
		ccPrintHex(get_idt_descriptor(i).ist, 0x0F);
		ccNewline();
		ccPrint("		+  TYPE_ATTRIBUTES ", 0x0F);
		ccPrintBin(get_idt_descriptor(i).type_attributes, 0x0F);
		ccNewline();
		ccPrint("		+  OFFSET_2: ", 0x0F);
		ccPrintHex(get_idt_descriptor(i).offset_2, 0x0F);
		ccNewline();
		ccPrint("		+  OFFSET_3: ", 0x0F);
		ccPrintHex(get_idt_descriptor(i).offset_3, 0x0F);
		ccNewline();
}
