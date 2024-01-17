package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class PatientToevoegen {
    public PatientToevoegen(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label patientName, patientGeboortedatum, patientContactgegevens;
        final TextField patientNameInput, patientGeboortedatumInput, patientContactInput;
        final Button VoegPatientToe;

        patientName = new Label("Naam patient");
        patientName.setLayoutX(357);
        patientName.setLayoutY(375);

        patientNameInput = new TextField();
        patientNameInput.setLayoutX(557);
        patientNameInput.setLayoutY(375);

        patientGeboortedatum = new Label("Geboortedatum patient");
        patientGeboortedatum.setLayoutX(357);
        patientGeboortedatum.setLayoutY(427);

        patientGeboortedatumInput = new TextField();
        patientGeboortedatumInput.setLayoutX(557);
        patientGeboortedatumInput.setLayoutY(427);

        patientContactgegevens = new Label("Contactgegevens patient");
        patientContactgegevens.setLayoutX(357);
        patientContactgegevens.setLayoutY(479);

        patientContactInput = new TextField();
        patientContactInput.setLayoutX(557);
        patientContactInput.setLayoutY(479);

        VoegPatientToe = new Button("Opslaan");
        VoegPatientToe.setLayoutX(560);
        VoegPatientToe.setLayoutY(630);

        VoegPatientToe.setOnAction(e->{
            Database database = new Database();
            String Naam = patientNameInput.getText();
            String Geboortedatum = patientGeboortedatumInput.getText();
            String Contactgegevens = patientContactInput.getText();
            database.toevoegenPatient(Naam, Geboortedatum, Contactgegevens);

            Patient patient = new Patient(stage);
        });






    root.getChildren().addAll(patientName, patientNameInput, patientGeboortedatum, patientGeboortedatumInput, patientContactgegevens, patientContactInput, VoegPatientToe);
        stage.setTitle("Zorg op Maat patient toevoegen");
        stage.setScene(scene);
    }
}
