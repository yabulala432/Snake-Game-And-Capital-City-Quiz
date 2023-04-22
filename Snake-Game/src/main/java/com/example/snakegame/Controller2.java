package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    ChoiceBox<Integer> choiceBoxId = new ChoiceBox<>();
    @FXML
    Button proceedId = new Button();
    @FXML
    Label scoreLabelId = new Label();
    ArrayList<Integer> choiceBoxLists = new ArrayList<>();
    //    Files files = new Files();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChoiceBoxId();
        choiceBoxId.getItems().addAll(choiceBoxLists);
        choiceBoxId.setOnAction(this::getChoiceBox);
        proceedId.setDisable(true);
    }

    private void setChoiceBoxId() {
//        for (int i = 0; i < files.sizeOfAllInfo(); i++) {
        for (int i = 0; i < 5; i++) {
            choiceBoxLists.add((i + 1)*5);
        }
    }

    public void getChoiceBox(ActionEvent event) {
        Controller.gameEnder = choiceBoxId.getValue();
        proceedId.setDisable(false);
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        scene.setFill(Color.GRAY.brighter());
        stage.show();
    }
    public void setScoreLabelId(String score,String outOf){
        scoreLabelId.setText("Score: " + score + " Out of: " + outOf);
    }

}
