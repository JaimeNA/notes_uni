#include<stdio.h>

int fibonacci(int n) {
	if (n < 2)
		return 1;

	return fibonacci(n-1) + fibonacci(n-2);
}

int main() {
	int n = 5;

	printf("The %d fibonacci number is %d", n, fibonacci(n));

	return 0;
}
