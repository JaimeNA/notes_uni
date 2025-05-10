#include <colorConsole.h>

static uint32_t uintToBase(uint64_t value, char * buffer, uint32_t base);

static char buffer[64] = { '0' };
static uint8_t * const video = (uint8_t*)0xB8000;
static uint8_t * currentVideo = (uint8_t*)0xB8000;
static const uint32_t width = 80;
static const uint32_t height = 25 ;

void ccPrint(const char * string, uint8_t color)
{
	int i;

	for (i = 0; string[i] != 0; i++)
		ccPrintChar(string[i], color);
}

void ccPrintChar(char character, uint8_t color)
{
	*currentVideo = character;
	currentVideo++;
	*currentVideo = color;
	currentVideo++;
}

void ccNewline()
{
	do
	{
		ccPrintChar(' ', 0x00);
	}
	while((uint64_t)(currentVideo - video) % (width * 2) != 0);
}

void ccPrintDec(uint64_t value, uint8_t color)
{
	ccPrintBase(value, 10, color);
}

void ccPrintHex(uint64_t value, uint8_t color)
{
	ccPrintBase(value, 16, color);
}

void ccPrintBin(uint64_t value, uint8_t color)
{
	ccPrintBase(value, 2, color);
}

void ccPrintBase(uint64_t value, uint32_t base, uint8_t color)
{
    uintToBase(value, buffer, base);
    ccPrint(buffer, color);
}

void ccClear()
{
	int i;

	for (i = 0; i < height * width; i++)
		video[i * 2] = ' ';
	currentVideo = video;
}

static uint32_t uintToBase(uint64_t value, char * buffer, uint32_t base)
{
	char *p = buffer;
	char *p1, *p2;
	uint32_t digits = 0;

	//Calculate characters for each digit
	do
	{
		uint32_t remainder = value % base;
		*p++ = (remainder < 10) ? remainder + '0' : remainder + 'A' - 10;
		digits++;
	}
	while (value /= base);

	// Terminate string in buffer.
	*p = 0;

	//Reverse string in buffer.
	p1 = buffer;
	p2 = p - 1;
	while (p1 < p2)
	{
		char tmp = *p1;
		*p1 = *p2;
		*p2 = tmp;
		p1++;
		p2--;
	}

	return digits;
}
