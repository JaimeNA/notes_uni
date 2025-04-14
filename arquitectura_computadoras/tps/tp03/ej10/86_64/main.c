#include<stdio.h>

int fibonacci(int n);

int main() {
	int n = 5;

	for(int i = 0; i < 10; i++) {
		printf("The %d fibonacci number is %d\n", i, fibonacci(i));
	}
	return 0;
}
