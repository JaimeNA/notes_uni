#include<miniGame.h>
#include <math.h>
typedef struct{
    int x;
    int y;
} Vector;

#define MAX_SPEED 15

Vector position;
Vector velocity;
Vector acceleration;

void gameInit() {

    position.x = 100;
    position.y = 100;

    velocity.x = 0; // Caracteres por segundo
    velocity.y = 0;

    acceleration.y = 1;
    acceleration.x = 1;

}

int abs(int value) {
    if (value < 0)
        return value * -1;

    return value;
}

void gameUpdate() {
    position.x += velocity.x;
    position.y += velocity.y;
}

int gameGetX(){
    return position.x / 10;
}
int gameGetY(){
    return position.y / 10;
}

void gameSetVelX(int vel){
    position.x += vel;
}

void gameSetVelY(int vel){
    position.y += vel;
}

