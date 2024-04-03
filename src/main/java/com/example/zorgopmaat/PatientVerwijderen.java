package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PatientVerwijderen {

    private Stage stage;
    private Database database;
    private ComboBox<String> patientComboBox; // Verplaats de ComboBox naar een klasse variabele

    public PatientVerwijderen(Stage stage) {
        this.stage = stage;
        this.database = new Database();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        Image image = new Image(Main.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(515);
        logo.setLayoutY(32);

        Button terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> {
            Patient patient = new Patient(stage);
        });


        patientComboBox = new ComboBox<>();
        patientComboBox.setLayoutX(580);
        patientComboBox.setLayoutY(400);
        patientComboBox.getItems().addAll(haalPatientNamenOp());

        Button verwijderBtn = new Button("Verwijder patiënt");
        verwijderBtn.setLayoutX(580);
        verwijderBtn.setLayoutY(450);
        verwijderBtn.setOnAction(actionEvent -> verwijderPatient(patientComboBox.getValue()));

        root.getChildren().addAll(logo, terugBtn, patientComboBox, verwijderBtn);

        this.stage.setTitle("Patiënt Verwijderen");
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void verwijderPatient(String patientNaam) {
        if (patientNaam != null && !patientNaam.isEmpty()) {
            database.verwijderPatient(patientNaam);

            // Venster geeft weer dat patient verwijderd is
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Verwijderen Gelukt");
            alert.setHeaderText(null);
            alert.setContentText("Patiënt " + patientNaam + " is succesvol verwijderd.");
            alert.showAndWait();

            // Update de ComboBox zodat de patient niet meer zichtbaar is
            patientComboBox.getItems().setAll(haalPatientNamenOp());
        } else {

            // Waarschuwingsvenster als er geen patiënt is geselecteerd
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Geen Patiënt Geselecteerd");
            alert.setHeaderText(null);
            alert.setContentText("Selecteer een patiënt om te verwijderen.");
            alert.showAndWait();
        }
    }



    private List<String> haalPatientNamenOp() {
        List<PatientKeuze> patienten = database.fetchPatientenFromDatabase();
        List<String> namen = new ArrayList<>();
        for (PatientKeuze patient : patienten) {
            namen.add(patient.toString()); // Gebruik toString methode voor de patiëntnaam
        }
        return namen;
    }
}
