#include <stdint.h>
#include <string.h>
#include <lib.h>

#include <moduleLoader.h>
#include <naiveConsole.h>
#include <idtLoader.h>
#include <keyboard.h>
#include <sysCalls.h>
#include <colorConsole.h>

extern uint8_t rtc(uint8_t selection);

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

uint8_t getHours() {
	return rtc(4);
}

uint8_t getMinutes() {
	return rtc(2);
}

uint8_t getSeconds() {
	return rtc(0);
}

void printTime(){
	int hours = getHours();
	int	minutes = getMinutes();
	int	seconds = getSeconds();

	ccPrint("Current time: ", 0x0F);
	ccPrintHex(hours, 0x0F);	// Esta en UTC
	ccPrint(":", 0x0F);
	ccPrintHex(minutes, 0x0F);
	ccPrint(":", 0x0F);
	ccPrintHex(seconds, 0x0F);

	ccNewline();
}

int main()
{	
	load_idt();
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

	write(2, "ERROR", 2);

	int prev = getSeconds();
	
	while(1) {
		if (getSeconds() != prev) {
			ccClear();
			printTime();
			prev = getSeconds();
		}
	}
	return 0;
}


