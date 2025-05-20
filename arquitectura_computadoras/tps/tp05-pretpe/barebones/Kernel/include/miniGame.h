#ifndef MINIGAME_H
#define MINIGAME_H

void gameInit();
void gameUpdate();

int gameGetX();
int gameGetY();

void gameSetVelX(int vel);
void gameSetVelY(int vel);

#endif