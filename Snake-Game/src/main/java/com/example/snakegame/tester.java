package com.example.snakegame;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class tester {
    public void main(String[] args) {
        System.out.println('h');
//        Image image = new Image("");
        Image image = new Image(getClass().getResource("za.jpg").toExternalForm());
        ImageView iv = new ImageView(image);;

    }
}
