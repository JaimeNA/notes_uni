#include <stdint.h>
#include <string.h>
#include <lib.h>
#include <moduleLoader.h>
#include <naiveConsole.h>
#include <colorConsole.h>

extern uint8_t rtc(uint8_t selection);
extern uint8_t keyboard_status();
extern char keyboard_output();


extern uint8_t text;
extern uint8_t rodata;
extern uint8_t data;
extern uint8_t bss;
extern uint8_t endOfKernelBinary;
extern uint8_t endOfKernel;

static const uint64_t PageSize = 0x1000;

static void * const sampleCodeModuleAddress = (void*)0x400000;
static void * const sampleDataModuleAddress = (void*)0x500000;

typedef int (*EntryPoint)();


void clearBSS(void * bssAddress, uint64_t bssSize)
{
	memset(bssAddress, 0, bssSize);
}

void * getStackBase()
{
	return (void*)(
		(uint64_t)&endOfKernel
		+ PageSize * 8				//The size of the stack itself, 32KiB
		- sizeof(uint64_t)			//Begin at the top of the stack
	);
}

void * initializeKernelBinary()
{
	char buffer[10];

	ncPrint("[x64BareBones]");
	ncNewline();

	ncPrint("CPU Vendor:");
	ncPrint(cpuVendor(buffer));
	ncNewline();

	ncPrint("[Loading modules]");
	ncNewline();
	void * moduleAddresses[] = {
		sampleCodeModuleAddress,
		sampleDataModuleAddress
	};

	loadModules(&endOfKernelBinary, moduleAddresses);
	ncPrint("[Done]");
	ncNewline();
	ncNewline();

	ncPrint("[Initializing kernel's binary]");
	ncNewline();

	clearBSS(&bss, &endOfKernel - &bss);

	ncPrint("  text: 0x");
	ncPrintHex((uint64_t)&text);
	ncNewline();
	ncPrint("  rodata: 0x");
	ncPrintHex((uint64_t)&rodata);
	ncNewline();
	ncPrint("  data: 0x");
	ncPrintHex((uint64_t)&data);
	ncNewline();
	ncPrint("  bss: 0x");
	ncPrintHex((uint64_t)&bss);
	ncNewline();

	ncPrint("[Done]");
	ncNewline();
	ncNewline();
	return getStackBase();
}

int getHours() {
	return rtc(4);
}

void printTime(){
	int hours = getHours();
	int	minutes = (hours * 60) % 60;
	int	seconds = (minutes * 60) % 60;

	ccPrint("Current time: ", 0x0F);
	ccPrintDec(hours, 0x0F);
	ccPrint(":", 0x0F);
	ccPrintDec(minutes, 0x0F);
	ccPrint(":", 0x0F);
	ccPrintDec(seconds, 0x0F);

	ccNewline();
}

uint8_t pollKeyboard() {
	while(!(keyboard_status() & 0x01));
	return keyboard_output();
}

char kbd_US [128] =
{
    0,  27, '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', '\b',   
  '\t', /* <-- Tab */
  'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\n',     
    0, /* <-- control key */
  'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\'', '`',  0, '\\', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/',   0,
  '*',
    0,  /* Alt */
  ' ',  /* Space bar */
    0,  /* Caps lock */
    0,  /* 59 - F1 key ... > */
    0,   0,   0,   0,   0,   0,   0,   0,
    0,  /* < ... F10 */
    0,  /* 69 - Num lock/
    0,  /* Scroll Lock */
    0,  /* Home key */
    0,  /* Up Arrow */
    0,  /* Page Up */
  '-',
    0,  /* Left Arrow */
    0,
    0,  /* Right Arrow */
  '+',
    0,  /* 79 - End key*/
    0,  /* Down Arrow */
    0,  /* Page Down */
    0,  /* Insert Key */
    0,  /* Delete Key */
    0,   0,   0,
    0,  /* F11 Key */
    0,  /* F12 Key */
    0,  /* All other keys are undefined */
};

static uint16_t * const idt_start = (uint16_t*)0x0000;
static uint16_t * const idt_end = (uint16_t*)0xFFFF;

struct InterruptDescriptor64 {
	uint16_t offset_1;        // offset bits 0..15
   	uint16_t selector;        // a code segment selector in GDT or LDT
   	uint8_t  ist;             // bits 0..2 holds Interrupt Stack Table offset, rest of bits zero.
   	uint8_t  type_attributes; // gate type, dpl, and p fields
   	uint16_t offset_2;        // offset bits 16..31
   	uint32_t offset_3;        // offset bits 32..63
   	uint32_t zero;            // reserved
};

// TODO: Move to its own file
struct InterruptDescriptor64 get_idt_descriptor(uint8_t index) {
	uint16_t*  desc_ptr = *(idt_start + index*16);	// Each index is 16 bytes(128 bits)

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
	uint16_t*  desc_ptr = *(idt_start + index*16 + IDT_OFFSET);	// Each index is 16 bytes(128 bits)

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

int main()
{	
	ncPrint("[Kernel Main]");
	ncNewline();
	ncPrint("  Sample code module at 0x");
	ncPrintHex((uint64_t)sampleCodeModuleAddress);
	ncNewline();
	ncPrint("  Calling the sample code module returned: ");
	ncPrintHex(((EntryPoint)sampleCodeModuleAddress)());
	ncNewline();
	ncNewline();

	ncPrint("  Sample data module at 0x");
	ncPrintHex((uint64_t)sampleDataModuleAddress);
	ncNewline();
	ncPrint("  Sample data module contents: ");
	ncPrint((char*)sampleDataModuleAddress);
	ncNewline();

	ncPrint("[Finished]");

	ncClear();

	ccPrint("ARQUITECTURA DE COMPUTADORAS", 0xF2);
	ccNewline();
	ccNewline();
	ccNewline();

	printTime();

	ccNewline();
	ccPrint("DESCRIPTORS: ", 0x0F);

	for (int i = 0; i < 3 ; i ++) {
		printDescripor(i);
	}

	ccNewline();
	ccNewline();
	// for (int i = 0; i < 100; i++) {
	// 	uint8_t scancode = pollKeyboard();
	// 	uint8_t key = kbd_US[scancode];

	// 	if (key != 0)
	// 		ccPrintChar(key, 0x0F);
		
	// 	ccPrint(" ", 0x0F);
	// }

	return 0;
}


