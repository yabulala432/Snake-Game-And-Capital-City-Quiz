package com.example.snakegame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//import java.util.Random;
public class Block extends Rectangle {
    //    Random random = new Random();
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    int direction = LEFT;// not private
    int posX, posY, oldPosX, oldPosY;
    // to get the current position of the block and to store it in the old ones
    // to make another blocks to follow the previous one
    int maxX, maxY;
    // to check the maximum limit of the borders the min is obviously 0;
    Block previous;

    public Block(int x, int y, Block p, Field f) {
        super(SnakeApplication.blockSize, SnakeApplication.blockSize);//setting the block size in pixels
        posX = x;
        posY = y;
        setStroke(Color.INDIANRED.brighter());
        setTranslateX(x * SnakeApplication.blockSize);
        setTranslateY(y * SnakeApplication.blockSize);

        previous = p;

        maxX = f.getW();
        maxY = f.getH();
    }

    public void update() {
        oldPosY = posY;
        oldPosX = posX;
//        int direction = random.nextInt(1,5);
        // checking the block is head or body
        if (previous == null) {
            // that means if head
            switch (direction) {
                case UP -> moveUp();
                case DOWN -> moveDown();
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }
        } else {
//  if not head then only update by having reference from the old position traces from the head block
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }
        updatePosition();
    }

    //    the actual movement in the screen
    private void updatePosition() {
//        System.out.println(posX + "/" +posY);
        //updating visually
        setTranslateX(posX * SnakeApplication.blockSize);
        setTranslateY(posY * SnakeApplication.blockSize);
    }

    //changing the positions for the coordinates of x and y
    private void moveUp() {
        posY--;
        if (posY < 0) {
            posY = maxY-1;
        }
    }

    private void moveDown() {
        posY++;
        if (posY == maxY) {
            posY = 0;
        }
    }

    private void moveLeft() {
        posX--;
        if (posX < 0) {
            posX = maxX-1;
        }
    }

    private void moveRight() {
        posX++;
        if (posX == maxX) {
            posX = 0;
        }
    }

}
