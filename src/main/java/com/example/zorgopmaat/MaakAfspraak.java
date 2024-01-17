// MaakAfspraak.java
package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class MaakAfspraak {

    private ChoiceBox<ZorgverlenerKeuze> zorgVerlenerChoiceBox;
    private ChoiceBox<PatientKeuze> patientChoiceBox;
    private TextField naamPatientInput, tijdAfspraakInput, datumAfspraakInput, locatieAfspraakInput;
    private Button terugBtn, VoegAfspraakToe;

    private Database databaseHandler;

    public MaakAfspraak(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        // Initialisatie van de databasehandler
        databaseHandler = new Database();

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> {
            Patient patient = new Patient(stage);
        });

        patientChoiceBox = new ChoiceBox<>();
        patientChoiceBox.setLayoutX(500);
        patientChoiceBox.setLayoutY(350);

        zorgVerlenerChoiceBox = new ChoiceBox<>();
        zorgVerlenerChoiceBox.setLayoutX(500);
        zorgVerlenerChoiceBox.setLayoutY(400);

        tijdAfspraakInput = new TextField();
        tijdAfspraakInput.setLayoutX(500);
        tijdAfspraakInput.setLayoutY(450);

        datumAfspraakInput = new TextField();
        datumAfspraakInput.setLayoutX(500);
        datumAfspraakInput.setLayoutY(500);

        locatieAfspraakInput = new TextField();
        locatieAfspraakInput.setLayoutX(500);
        locatieAfspraakInput.setLayoutY(550);

        VoegAfspraakToe = new Button("Opslaan");
        VoegAfspraakToe.setLayoutX(500);
        VoegAfspraakToe.setLayoutY(650);
        VoegAfspraakToe.setOnAction(e -> {
            // Voeg je logica toe om de afspraak op te slaan
            voegAfspraakToe();
        });

        // Populate the ChoiceBoxes with values from the database
        populateZorgVerleners();
        populatePatienten();

        root.getChildren().addAll(terugBtn, patientChoiceBox, zorgVerlenerChoiceBox,
                tijdAfspraakInput, datumAfspraakInput, locatieAfspraakInput, VoegAfspraakToe);

        stage.setScene(scene);
    }

    private void populateZorgVerleners() {
        List<ZorgverlenerKeuze> zorgVerleners = databaseHandler.fetchZorgVerlenersFromDatabase();
        ObservableList<ZorgverlenerKeuze> zorgVerlenersList = FXCollections.observableArrayList(zorgVerleners);
        zorgVerlenerChoiceBox.setItems(zorgVerlenersList);
    }

    private void populatePatienten() {
        List<PatientKeuze> patienten = databaseHandler.fetchPatientenFromDatabase();
        ObservableList<PatientKeuze> patientenList = FXCollections.observableArrayList(patienten);
        patientChoiceBox.setItems(patientenList);
    }

    private void voegAfspraakToe() {
        // Ophalen van de benodigde gegevens
        String naamPatient = naamPatientInput.getText();
        int zorgverlenerId = zorgVerlenerChoiceBox.getValue().getZorgverlenerId();
        int patientId = patientChoiceBox.getValue().getPatientId();
        String datum = datumAfspraakInput.getText();
        String tijd = tijdAfspraakInput.getText();
        String locatie = locatieAfspraakInput.getText();

        // Voeg de afspraak toe via de databasehandler
        databaseHandler.toevoegenAfspraak(patientId, zorgverlenerId, datum, tijd, locatie);

        // Voeg hier eventueel verdere logica toe na het toevoegen van de afspraak
    }
}
