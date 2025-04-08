#include<stdio.h>

int factorial(int n);
int seven();

int main() {

	int n = 10;

	int res = factorial(n);

	printf("fact: %d\n", res);

	printf("Seven: %d /n", seven());

	return 0;
}

