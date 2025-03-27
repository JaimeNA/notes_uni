#include<stdio.h>

int main (int args, char *argv[]) {
	
	if (args < 2) {
		printf("Usage: ./string_alt [path]");

		return 0;
	}

	// Open file as binary
	FILE *file = fopen(argv[1], "rb");

	if (file == NULL) {
		printf("Error reading file");
		return -1;
	}

	// Read file
	char c = 'e';

	for (int i = 0; i < 100000; i++) {
		fread(&c, sizeof(char), 1, file);

		if ((c >= 32 && c <= 127) || c == '\n') {
			printf("%c", c);
		}
	}
	// Close file
	fclose(file);

	return 0;
}
