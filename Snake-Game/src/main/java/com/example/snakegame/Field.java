package com.example.snakegame;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Field extends Pane {


    private final int w, h;// for width and height of the pane
    ArrayList<Block> blocks = new ArrayList<>();
    Snake snake;
    Food food;
    public int SCORE = 0;

    public Field(int width, int height) {
        w = width;
        h = height;

        setMinSize(w * SnakeApplication.blockSize, h * SnakeApplication.blockSize);
        // we have multiplied the w with the pixel size in the main class to get the exact pixel
        setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addFood();
    }

    public void addSnake(Snake s) {
        snake = s;
        // to add the snake's body we have to iterate through the arraylist of the blocks
        for (Block block : s.blocks) {
            addBlock(block);
        }
    }

    private void addBlock(Block b) {
        getChildren().add(b);
        blocks.add(b);
    }

    public void update() {
        for (Block b : blocks) {
            b.update();
//      update each time the blocks
        }
//        System.out.println("food Y: " + food.getPosY() + " x: " + food.getPosX());
//        System.out.println("head Y: " + snake.head.posX + " x:" + snake.head.posY);
        if (isEaten(food)) {
            System.out.println("eaten");
            addFood();
            addTail();
            System.out.println(blocks.size());
            ++SCORE;
        }
    }

    public void addTail() {
        Block tail = new Block(snake.previous.oldPosX, snake.previous.oldPosY, snake.previous, this);
        snake.previous = tail;

        // to customize the colors only
        if (blocks.size() % 10 >= 2 && blocks.size() % 10 < 5) {
            tail.setFill(Color.GREEN.brighter());
        } else if (blocks.size() % 10 >= 5 && blocks.size() % 10 < 8) {
            System.out.println("blocksize % 10: " + blocks.size() % 10);
            tail.setFill(Color.BLACK.darker());
        } else {
            tail.setFill(Color.WHITESMOKE.grayscale());
        }

        addBlock(tail);
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void addFood() {
        int xRandom = randomGenerator();
        int yRandom = randomGenerator();
        Food newFood = new Food(xRandom, yRandom);
        getChildren().add(newFood);
        getChildren().remove(food);
        food = newFood;
    }

    private Boolean isEaten(Food theFood) {
        if (theFood == null) {
            return false;
        }// handling nullPointer exception;
        return theFood.getPosX() == snake.head.posX &&
                theFood.getPosY() == snake.head.posY;
        // it shouldn't be getters that are built inside the rectangle class for the head
        // because the ""delay"" will make it to return always ""false"" boolean
    }

    public boolean isGameOver() {
        boolean isOver = false;
        for (int i = 1; i < blocks.size(); i++) {
            if (snake.head.posX == blocks.get(i).posX && snake.head.posY == blocks.get(i).posY) {
                isOver = true;
//                System.out.println("over");
                break;
            }
//            isOver = false;
        }
        return isOver;
    }

    private int randomGenerator() {
        Random random = new Random();
        return random.nextInt(1, SnakeApplication.width-1);
    }


}
