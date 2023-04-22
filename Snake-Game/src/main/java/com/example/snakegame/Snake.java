package com.example.snakegame;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake {
    ArrayList<Block> blocks = new ArrayList<>();
    // the snake is just an array of the blocks
    Block head;// the head is the main part ot the snake it has to be treated differently
    Block previous;
    public Snake(int il, Field f) {
        // the initial position of x and y set at the center
        int ipx = (f.getW() / 2) - (SnakeApplication.blockSize / 2);
        int ipy = f.getH() / 2;

        head = new Block(ipx, ipy, null,f);
        head.setFill(Color.RED);
        blocks.add(head);
        // no previous block to follow

        previous = head;

        // obviously the first node or block to be followed is the head

        for (int i = 1; i < il; i++) {
            Block b = new Block(ipx + i, ipy, previous,f);
            b.setFill(Color.GREEN);
            previous = b;// the previous block has to be updated not to distort the movement of the blocks(snake)
            blocks.add(b);
        }
    }
    public void setDirection(int direction){
        head.direction = direction;
    }


    public int getDirection() {
        return head.direction;
    }
}
