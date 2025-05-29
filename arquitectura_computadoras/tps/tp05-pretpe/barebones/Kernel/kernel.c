#include <stdint.h>
#include <string.h>
#include <lib.h>

#include <moduleLoader.h>
#include <naiveConsole.h>
#include <idtLoader.h>
#include <gdt_utils.h>
#include <keyboard.h>
#include <sysCalls.h>
#include <colorConsole.h>
#include <time.h>
#include <miniGame.h>
#include <videoDriver.h>

extern uint8_t rtc(uint8_t selection);

extern uint8_t text;
extern uint8_t rodata;
extern uint8_t data;
extern uint8_t bss;
extern uint8_t endOfKernelBinary;
extern uint8_t endOfKernel;

static const uint64_t PageSize = 0x1000;

static void * const sampleCodeModuleAddress = (void*)0xA00000;
static void * const sampleDataModuleAddress = (void*)0xB00000;

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
	ncPrintDec((uint64_t)&bss);
	ncPrint(" (end of kernel: 0x");
	ncPrintHex((uint64_t)&endOfKernel);
	ncPrint(" bytes)");
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

void drawCircleAt(int x, int y) {

	for (int i = x; i < x + 20; i++) {
		for (int j = y; j < y + 20; j++) {

			if (j >= y + 10)
				putPixel(0x00000000 + x*y*1000, i, -0.1f*i*i + 10);
			else
				putPixel(0x00000000 + x*y*1000, i, 0.1f*i*i - 10);
		}
	}
}

int main()
{	
	load_idt();
	ncPrint("[Kernel Main]");
	ncNewline();
	ncPrint("  Sample code module at 0x");
	ncPrintDec((uint64_t)sampleCodeModuleAddress);
	ncNewline();

	// Initialize video driver
	videoInitialize();
	videoSetFontsize(16);

	ncPrint("[Finished]");

	ncPrintHex(((EntryPoint)sampleCodeModuleAddress)());
	while(1) {
		drawSquare(50, 50, 500, 0xFF00FF00);
		drawScreen();
	}

	// ncClear();

	// ccPrint("ARQUITECTURA DE COMPUTADORAS", 0xF2);
	// ccNewline();
	// ccNewline();
	// ccNewline();

	// printTime();

	// write(2, "ERROR", 2);

	// char buff[32];
	// int length = 32;

	// read(1, buff, length);

	// int lastTime = ticks_elapsed();
	// int deltaTime = 0;

	// gameInit();
	// int x = 0;
	// int y = 0;
	// int i = 0;
	

	return 0;
}


