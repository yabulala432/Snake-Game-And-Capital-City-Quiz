package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    @FXML
    Label questionerLabelId;
    @FXML
    Label countryLabelId;
    @FXML
    Label gainOrLostLabelId;
    @FXML
    Button oneButtonId;
    @FXML
    Button twoButtonId;
    @FXML
    Button fourButtonId;
    @FXML
    Button fiveButtonId;
    @FXML
    Button threeButtonId;
    @FXML
    Button sixButtonId;
    @FXML
    Button nextButtonId;
    @FXML
    Label counterId;
    @FXML
    Button replayId;
    public int ansIndex = 0;
    String one, two, three, four, five, six, countryName, exactAnswer;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static int gameEnder, percentage;

    public void setCounterId(String count) {
        counterId.setText(count);
    }

    public void setCountryId(String countryName) {
        countryLabelId.setText(countryName);
    }

    public void setQuestionerLabelId(String intro) {
        questionerLabelId.setText(intro);
    }

    public void setGainOrLostLabelId(String gainOrLost) {
        gainOrLostLabelId.setText(gainOrLost);
    }

    public void setOneButtonId(String capitalName) {
        oneButtonId.setText(capitalName);
        oneButtonId.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        oneButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(1))));
    }

    public void setTwoButtonId(String capitalName) {
        twoButtonId.setText(capitalName);
        twoButtonId.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        twoButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DASHED,null,new BorderWidths(1))));
    }

    public void setThreeButtonId(String capitalName) {
        threeButtonId.setText(capitalName);
        threeButtonId.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        threeButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(1))));
    }

    public void setFourButtonId(String capitalName) {
        fourButtonId.setText(capitalName);
        fourButtonId.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        fourButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DASHED,null,new BorderWidths(1))));
    }

    public void setFiveButtonId(String capitalName) {
        fiveButtonId.setText(capitalName);
        fiveButtonId.setBackground(new Background(new BackgroundFill(Color.GREEN,null,null)));
        fiveButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(1))));
    }

    public void setSixButtonId(String capitalName) {
        sixButtonId.setText(capitalName);
        sixButtonId.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,null,null)));
        sixButtonId.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DASHED,null,new BorderWidths(1))));
    }

    public void generate_the_quiz() {
        System.out.println(gameEnder);
        GenerateQuizes generateQuizes = new GenerateQuizes(new Files());

        one = generateQuizes.choices[0].toUpperCase();
        two = generateQuizes.choices[1].toUpperCase();
        three = generateQuizes.choices[2].toUpperCase();
        four = generateQuizes.choices[3].toUpperCase();
        five = generateQuizes.choices[4].toUpperCase();
        six = generateQuizes.choices[5].toUpperCase();
        countryName = generateQuizes.question.toUpperCase();
        exactAnswer = generateQuizes.answer.toUpperCase();

        setFiveButtonId(five);
        setSixButtonId(six);
        setFourButtonId(four);
        setThreeButtonId(three);
        setTwoButtonId(two);
        setOneButtonId(one);

        setQuestionerLabelId("What is the capital City of");
        setCountryId(countryName);
//        setGainOrLostLabelId("");

        setButtonsNotAccessible(true);
        replayId.setDisable(true);
    }

    int counter = 2;

    public void quizGeneratorWithEnd() {
        generate_the_quiz();
        setButtonsNotAccessible(false);
        if (counter == gameEnder + 1) {
            replayId.setDisable(false);
            nextButtonId.setDisable(true);
            counter = 0;
        } else if (counter == 0) {
            counterId.setText("Final Score: ");
        } else {
            counter++;
        }
    }

    int chancesToPlay = 0;

    void answerOfTheButtons(Button button) {
        button.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        if (Objects.equals(button.getText(), exactAnswer)) {
            ansIndex++;
            setGainOrLostLabelId(" Answered ");
            setCounterId(String.valueOf(ansIndex));
            setButtonsNotAccessible(true);
            chancesToPlay = 0;
        } else {
            setGainOrLostLabelId("You have left " + (chancesToPlay + 1) + " chance.");
            chancesToPlay++;
            if (chancesToPlay == 2) {
                setGainOrLostLabelId("NO! It is " + exactAnswer);
                setButtonsNotAccessible(true);
                chancesToPlay = 0;
            }
        }
    }

    public void oneAction() {
        answerOfTheButtons(oneButtonId);
    }

    public void twoAction() {
        answerOfTheButtons(twoButtonId);
    }

    public void threeAction() {
        answerOfTheButtons(threeButtonId);
    }

    public void fourAction() {
        answerOfTheButtons(fourButtonId);
    }

    public void fiveAction() {
        answerOfTheButtons(fiveButtonId);
    }

    public void sixAction() {
        answerOfTheButtons(sixButtonId);
    }

    private void setButtonsNotAccessible(boolean isNotAccessible) {
        oneButtonId.setDisable(isNotAccessible);
        twoButtonId.setDisable(isNotAccessible);
        threeButtonId.setDisable(isNotAccessible);
        fourButtonId.setDisable(isNotAccessible);
        fiveButtonId.setDisable(isNotAccessible);
        sixButtonId.setDisable(isNotAccessible);
    }

    public void switchToScene2(ActionEvent event) throws IOException {

        String var = counterId.getText();
        String var2  = String.valueOf(gameEnder);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main2.fxml"));
        root= fxmlLoader.load();
        Controller2 controller2 = fxmlLoader.getController();
        controller2.setScoreLabelId(var,var2);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToSnake(ActionEvent event) {
        Scene scene1 = SnakeApplication.root.getScene();
        stage  = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene1);
        stage.show();
    }
}