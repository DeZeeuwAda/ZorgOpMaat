package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Patient {

    public Patient(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");
        Styling.applyStylesheet(scene);

        final Button terugBtn, patientToevoegenBtn, patientVerwijderenBtn;

        Image image = new Image(Main.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(515);
        logo.setLayoutY(32);

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> {
            Overzicht overzicht = new Overzicht(stage);});


        patientToevoegenBtn = new Button(" Voeg patient toe");
        patientToevoegenBtn.setLayoutX(380);
        patientToevoegenBtn.setLayoutY(430);
        patientToevoegenBtn.setMinWidth(100);
        patientToevoegenBtn.setMinHeight(100);
        patientToevoegenBtn.setOnAction(actionEvent -> {
            PatientToevoegen patienttoevoegen = new PatientToevoegen(stage);});


        patientVerwijderenBtn = new Button(" Verwijder patient");
        patientVerwijderenBtn.setLayoutX(770);
        patientVerwijderenBtn.setLayoutY(430);
        patientVerwijderenBtn.setMinWidth(100);
        patientVerwijderenBtn.setMinHeight(100);
        patientVerwijderenBtn.setOnAction(actionEvent -> {
            PatientVerwijderen patientVerwijderen = new PatientVerwijderen(stage);
        });


        root.getChildren().addAll(terugBtn,logo, patientToevoegenBtn, patientVerwijderenBtn);
        stage.setTitle("Zorg op Maat patienten");
        stage.setScene(scene);
    }
}