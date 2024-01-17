package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ZorgVerlener {

    public ZorgVerlener(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label overzichtLabel;
        final Button terugBtn;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage);});


        overzichtLabel = new Label("Zorgverlener Info");
        overzichtLabel.setLayoutX(558);
        overzichtLabel.setLayoutY(159);

        root.getChildren().addAll(terugBtn, overzichtLabel);
        stage.setTitle("Zorg op Maat Zorgverlener");
        stage.setScene(scene);
    }
}

