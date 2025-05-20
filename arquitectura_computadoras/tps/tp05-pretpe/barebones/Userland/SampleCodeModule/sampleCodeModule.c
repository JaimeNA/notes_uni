/* sampleCodeModule.c */

#include "std.h"

extern int write(int fd, char * buff, int length);
int main() {

	while(1) {
		write(1, "asd", 3);
	}
	
	return 0;
}