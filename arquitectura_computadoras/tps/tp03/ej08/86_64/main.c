#include<stdio.h>

int kill(int pid);
ssize_t read(int fd, char *buffer, size_t size);
int write(int fd, char *buffer, size_t size);
int open(char *filename);
int close(unsigned int fd);

int strlen(char* str);	// Null terminated string

int main(int argc, char **argv) {

	char * test = "------ write test ------\n";

	write(1, test, 25);

	char buffer[20];
	int size = 20;

	int fd = open("file.txt");
	if (fd == -1)
		printf("Error opening file");

	for (int i = 0; i < 10; i++) {
		snprintf(buffer, size, "%d Una linea\n", i);
		int bytes_written = write(fd, buffer, size);	// Write to file

		if (bytes_written == -1)
			printf("Error writing to file");
		else
			printf("Wrote %d bytes to file\n", bytes_written);
	}

	if (!close(fd))
		printf("Succesfully closed file\n");
	else
		printf("Error closing file\n");

	return 0;
}

