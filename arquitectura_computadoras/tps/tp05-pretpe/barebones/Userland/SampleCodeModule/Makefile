include ../Makefile.inc

MODULE=0000-sampleCodeModule.bin
SOURCES=$(wildcard [^_]*.c)
OBJECTS=$(SOURCES:.c=.o)
SOURCES_ASM=$(wildcard asm/*.s)
OBJECTS_ASM=$(SOURCES_ASM:.asm=.o)
OBJECTS_C=$(SOURCES_C:.c=.o)

all: $(MODULE)

$(MODULE): $(OBJECTS) $(OBJECTS_ASM)
	$(LD) $(LDFLAGS) -T sampleCodeModule.ld -o ../$(MODULE) $(OBJECTS) $(OBJECTS_ASM)

%.o: %.c
	$(GCC) $(GCCFLAGS) -I./include -c $< _loader.c -o $@

%.o : %.asm
	$(ASM) $(ASMFLAGS) $< -o $@

clean:
	rm -rf asm/*.o *.o *.bin

.PHONY: all clean
