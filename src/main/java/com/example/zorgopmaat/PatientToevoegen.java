package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PatientToevoegen {

    public PatientToevoegen(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");
        Styling.applyStylesheet(scene);

        final Label patientName, patientGeboortedatum, patientContactgegevens;
        final TextField patientNameInput, patientGeboortedatumInput, patientContactInput;
        final Button terugBtn, VoegPatientToe;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> {
            Patient patient = new Patient(stage);});

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

            String Naam = patientNameInput.getText();
            String Geboortedatum = patientGeboortedatumInput.getText();
            String Contactgegevens = patientContactInput.getText();

            if (Naam.isEmpty() || Geboortedatum.isEmpty() || Contactgegevens.isEmpty()) {
                // Toon een melding dat alle velden moeten worden ingevuld
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Oeps!");
                alert.setHeaderText(null);
                alert.setContentText("Graag alle gegevens invullen.");
                alert.showAndWait();
            } else {
                // Alle velden zijn ingevuld, voeg zorgverlener toe aan database
                Database database = new Database();
                database.toevoegenPatient(Naam, Geboortedatum, Contactgegevens);
                Patient patient = new Patient(stage);
            }


        });

        root.getChildren().addAll(terugBtn, patientName, patientNameInput, patientGeboortedatum, patientGeboortedatumInput, patientContactgegevens, patientContactInput, VoegPatientToe);
        stage.setTitle("Zorg op Maat patient toevoegen");
        stage.setScene(scene);
    }
}
