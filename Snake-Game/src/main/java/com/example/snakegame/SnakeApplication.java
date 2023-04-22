package com.example.snakegame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class SnakeApplication extends Application {

    protected static int blockSize = 10;
    // the size of the single body of the snake in pixels
    public static int width = 50, height = 50;
    // capacity of the window in blocks width can hold 30 blocks and height can hold 15 blocks of the body
    static int initial_length = 9;
    Scene usersScene, gameScene;
    int Level = 1, highScoreInt = 0;
    public static VBox root;
    AnchorPane anchorPane;
    HBox hBox;
    Field field;
    private Stage stage;
    long then = System.nanoTime();
    // for our ActionTimer object
    Snake snake;
    AnimationTimer timer;
    Label highScore, scoreIntro;

    @Override
    public void start(Stage stage) {
        root = new VBox(20);
        root.setPadding(new Insets(15));

        field = new Field(width, height);
        snake = new Snake(initial_length, field);
        field.addSnake(snake);

        hBox = new HBox(300);
        Label score = new Label("Score: 0");
        score.setFont(new Font("Verdana Bold Italic", 24));

        Button exit = new Button("EXIT");
        exit.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        exit.setShape(new Circle(20));
        exit.setOnAction(this::exitAction);

        hBox.getChildren().addAll(score, exit);

        root.getChildren().addAll(field, hBox);
        gameScene = new Scene(root);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - then > 100000000 / Level) {
                    field.update();
                    if (field.isGameOver()) {
                        exitAction(new ActionEvent());

                    }
                    score.setText("Score: " + field.SCORE);
                    then = now;
                }
            }
        };

        stage.setTitle("$4Y Snake Game!");

        anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 600);

        try {
            Image logo = new Image(
                    String.valueOf(
                            Objects.requireNonNull(
                                            getClass().
                                                    getResource("logo.png"))
                                    .toURI()
                    )
            );
            stage.getIcons().add(logo);
            anchorPane.setBackground(new Background(new BackgroundImage(
                    logo, NO_REPEAT, NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(
                    100, 100, true, true, true, true))));
        } catch (URISyntaxException ignore) {
        }

        scoreIntro = new Label("WELCOME");
        scoreIntro.setPrefHeight(60);
        scoreIntro.setLayoutX(190);
        scoreIntro.setLayoutY(0);
        scoreIntro.setTextFill(Color.GREEN.brighter());
        scoreIntro.setTextAlignment(TextAlignment.CENTER);
        scoreIntro.setFont(new Font("Veranda Bold", 45));

        highScore = new Label("HIGH SCORE: " + highScoreInt);
        highScore.setPrefHeight(60);
        highScore.setLayoutX(190);
        highScore.setLayoutY(50);
        highScore.setTextFill(Color.GREEN.brighter());
        highScore.setTextAlignment(TextAlignment.CENTER);
        highScore.setFont(new Font("Veranda Bolder Italic", 35));

        Button easy = new Button("EASY");
        easy.setLayoutX(114.0);
        easy.setLayoutY(226.0 + 140.0);
        easy.setFont(new Font("Veranda Bold", 23));
        easy.setOnAction(this::easyAction);
        easy.setTextFill(Color.YELLOW);
        easy.setBackground(new Background(new BackgroundFill(Color.GREEN.darker(), null, null)));
        easy.setShape(new Ellipse(50, 100));

        Button medium = new Button("MEDIUM");
        medium.setMnemonicParsing(false);
        medium.setLayoutX(240.0);
        medium.setLayoutY(226.0 + 140.0);
        medium.setTextFill(Color.WHITESMOKE);
        medium.setFont(new Font("Veranda Bold", 25));
        medium.setShape(new Ellipse(50, 130));
        medium.setBackground(new Background(new BackgroundFill(Color.PURPLE.saturate(), null, null)));
        medium.setOnAction(this::mediumAction);

        Button guru = new Button("GURU");
        guru.setOnAction(this::guruAction);
        guru.setLayoutX(410.0);
        guru.setLayoutY(226.0 + 140.0);
        guru.setFont(new Font("Veranda Bold", 22));
        guru.setTextFill(Color.YELLOW);
        guru.setBackground(new Background(new BackgroundFill(Color.GREEN.darker(), null, null)));
        guru.setShape(new Ellipse(50, 100));

        Button goToCapitalCity = new Button("Another Game?");
        goToCapitalCity.setTextFill(Color.WHITESMOKE);
        goToCapitalCity.setFont(new Font("Verdana", 25));
        goToCapitalCity.setShape(new Ellipse(70, 500));
        goToCapitalCity.setBackground(new Background(new BackgroundFill(Color.GREEN.darker(), null, null)));
        goToCapitalCity.setOnAction(this::gotoCapitalCityAction);
        goToCapitalCity.setLayoutY(526);
        goToCapitalCity.setLayoutX(185);
        anchorPane.getChildren().addAll(scoreIntro, highScore, easy, medium, guru, goToCapitalCity);
        usersScene = new Scene(anchorPane);

        stage.setScene(usersScene);
        // it must not be resizable because if it is set to be, then it will be difficult to control the game
        stage.setResizable(false);
        stage.show();
    }// these are button functions to control the level and to switch scenes

    private void gotoCapitalCityAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main2.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ignore) {

        }
    }

    private void startGame(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //otherwise throws nullPointerException
        controlMovements();
        stage.setScene(gameScene);
        timer.start();
    }

    private void easyAction(ActionEvent event) {
        Level = 1;
        startGame(event);
    }

    private void mediumAction(ActionEvent event) {
        Level = 4;
        startGame(event);
    }

    private void guruAction(ActionEvent event) {
        Level = 8;
        startGame(event);
    }

    public void exitAction(ActionEvent event) {
        stage.setScene(usersScene);
        if (highScoreInt < field.SCORE) {
            highScoreInt = field.SCORE;
            highScore.setText("High Score: " + highScoreInt);
            scoreIntro.setText("Game Over !!!");
            field.SCORE = 0;
        }
        for (int i = field.blocks.size() - 1; i >= 9; i--) {
            Block removedBlocks = field.blocks.remove(i);
            field.getChildren().remove(removedBlocks);
        }
        snake.previous = field.blocks.get(8);
        field.SCORE = 0;
    }

    private void controlMovements() {
        gameScene.setOnKeyPressed(keyEvent -> {
            if ((keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.W)) &&
                    field.snake.getDirection() != Block.DOWN) {
                field.snake.setDirection(Block.UP);
            } else if ((keyEvent.getCode().equals(KeyCode.LEFT) || keyEvent.getCode().equals(KeyCode.A)) &&
                    field.snake.getDirection() != Block.RIGHT) {
                field.snake.setDirection(Block.LEFT);
            } else if ((keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.D)) &&
                    field.snake.getDirection() != Block.LEFT) {
                field.snake.setDirection(Block.RIGHT);
            } else if ((keyEvent.getCode().equals(KeyCode.DOWN) || keyEvent.getCode().equals(KeyCode.S)) &&
                    field.snake.getDirection() != Block.UP) {
                field.snake.setDirection(Block.DOWN);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}