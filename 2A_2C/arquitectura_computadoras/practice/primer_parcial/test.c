#include <stdio.h>

struct est{
	int a;
	char b;
	float c;
};

void test(struct est *test_struct) {
	printf("Struct: \n");

	printf("a: %d, b: %c, c: %f\n", test_struct->a, test_struct->b, test_struct->c);
}

int main(int argc, char* argv[]) {

	struct est test_struct;
	test_struct.a = 14;
	test_struct.b = 'd';
	test_struct.c = 23.4f;

	test(&test_struct);
	
	return 0;
} 
