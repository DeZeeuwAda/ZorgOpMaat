package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Patient {

    public Patient(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label overzichtLabel, patientLabel, diagnoseLabel, zorgPlanLabel,zorgVerlenerLabel, behandelLocatieLabel;
        final Button terugBtn, patientToevoegen, patientVerwijderen;

        Image image = new Image(HelloApplication.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(515);
        logo.setLayoutY(32);

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage);});

        overzichtLabel = new Label("Patient info");
        overzichtLabel.setLayoutX(640);
        overzichtLabel.setLayoutY(250);


        patientLabel = new Label("Patient");
        patientLabel.setLayoutX(300);
        patientLabel.setLayoutY(300);

        diagnoseLabel = new Label("Diagnose");
        diagnoseLabel.setLayoutX(450);
        diagnoseLabel.setLayoutY(300);

        zorgPlanLabel = new Label("Plan van aanpak");
        zorgPlanLabel.setLayoutX(600);
        zorgPlanLabel.setLayoutY(300);

        zorgVerlenerLabel = new Label("Zorgverlener");
        zorgVerlenerLabel.setLayoutX(750);
        zorgVerlenerLabel.setLayoutY(300);

        behandelLocatieLabel = new Label("Adres");
        behandelLocatieLabel.setLayoutX(900);
        behandelLocatieLabel.setLayoutY(300);


        patientToevoegen = new Button(" Voeg patient toe");
        patientToevoegen.setLayoutX(110);
        patientToevoegen.setLayoutY(620);
        patientToevoegen.setOnAction(actionEvent -> { PatientToevoegen patienttoevoegen = new PatientToevoegen(stage);});


        patientVerwijderen = new Button(" Verwijder patient");
        patientVerwijderen.setLayoutX(110);
        patientVerwijderen.setLayoutY(650);


        root.getChildren().addAll(terugBtn,logo, overzichtLabel, patientToevoegen, diagnoseLabel, zorgPlanLabel, zorgVerlenerLabel, behandelLocatieLabel, patientVerwijderen);
        stage.setTitle("Zorg op Maat patienten");
        stage.setScene(scene);
    }
}

