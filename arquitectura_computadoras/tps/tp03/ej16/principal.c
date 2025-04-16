#include <stdio.h>

int checkLong(char *str);

int main() {
	
	char str[6] = {'h', 'o', 'l', 'a', 0, 3};

	if (checkLong(str) == 0)
		printf("Correct\n");
	else
		printf("Incorrect by %d\n", checkLong(str));
	
	return 0;
}

