package com.example.snakegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {
    private int posX, posY;

    public Food(int xPos, int yPos) {
        super(SnakeApplication.blockSize, SnakeApplication.blockSize);
        posX = xPos;
        posY = yPos;

        setTranslateX(posX * SnakeApplication.blockSize);
        setTranslateY(posY * SnakeApplication.blockSize);

        setFill(Color.PINK);
        setStroke(Color.RED);

    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
