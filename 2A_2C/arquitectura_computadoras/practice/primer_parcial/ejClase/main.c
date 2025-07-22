#include<stdio.h>

extern int toUpper(char str[]);

int main() {

	char str[] = "prueba de   la  funcion, (!@*&#, lasdnk ajsdfkl auiosdf  aewy aifu  d d  oaouidf ";

	int count = toUpper(str);
	printf("Salida: %s, se procesador %d letras", str, count);

	return 0;
}
