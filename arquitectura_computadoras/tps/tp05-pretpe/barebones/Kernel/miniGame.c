#include<miniGame.h>
#include <math.h>
typedef struct{
    int x;
    int y;
} Vector;

#define MAX_SPEED 300

Vector position;
Vector velocity;
Vector acceleration;

void gameInit() {

    position.x = 100;
    position.y = 0;

    velocity.x = 130; // Caracteres por segundo
    velocity.y = 50;

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

    if (position.x < 0 || position.x > 6000)
        velocity.x *= -1;

    if (position.y < 0 || position.y > 6000)
        velocity.y *= -1;
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

