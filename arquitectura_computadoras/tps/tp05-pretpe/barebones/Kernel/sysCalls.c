#include <sysCalls.h>
#include<colorConsole.h>

int write(int fd, char * buff, int length) {
    switch (fd) {
        case 1:
            ccPrint(buff, 0x0F);
            break;

        case 2:
            ccPrint(buff, 0x04);
            break;
    };

    return length;  // TODO: improve
}