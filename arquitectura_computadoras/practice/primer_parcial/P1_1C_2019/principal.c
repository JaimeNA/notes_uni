#include <stdio.h>

int checkLong(char* str, int length);

int main (int argc, char* argv[]) {
    char* str = "Test";
    int length = 3;

    int result = checkLong(str, length);

    if (!result) {
        printf("Verificacion exitosa\n");
    } else {
        printf("Verificacion fallida, la diferencia es %d \n", result);
    }

    return 0;
}