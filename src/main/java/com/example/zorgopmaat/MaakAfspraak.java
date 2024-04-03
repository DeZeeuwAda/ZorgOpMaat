package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MaakAfspraak {

    private ChoiceBox<ZorgverlenerKeuze> zorgVerlenerChoiceBox;
    private ChoiceBox<PatientKeuze> patientChoiceBox;
    private TextField tijdAfspraakInput, locatieAfspraakInput;
    private Button terugBtn, VoegAfspraakToe;

    private Label patientName, zorgVerlenerNaam, tijdAfspraakLabel, locatieAfspraakLabel, datumAfspraakLabel;

    private DatePicker datumAfspraakDatePicker; // Voeg DatePicker toe

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
            Afspraak afspraak = new Afspraak(stage);
        });

        patientName = new Label("Naam Patient");
        patientName.setLayoutX(357);
        patientName.setLayoutY(350);

        patientChoiceBox = new ChoiceBox<>();
        patientChoiceBox.setLayoutX(500);
        patientChoiceBox.setLayoutY(350);

        zorgVerlenerNaam = new Label("Naam zorgverlener");
        zorgVerlenerNaam.setLayoutX(357);
        zorgVerlenerNaam.setLayoutY(400);

        zorgVerlenerChoiceBox = new ChoiceBox<>();
        zorgVerlenerChoiceBox.setLayoutX(500);
        zorgVerlenerChoiceBox.setLayoutY(400);

        tijdAfspraakLabel = new Label("Tijd afspraak");
        tijdAfspraakLabel.setLayoutX(357);
        tijdAfspraakLabel.setLayoutY(450);

        tijdAfspraakInput = new TextField();
        tijdAfspraakInput.setLayoutX(500);
        tijdAfspraakInput.setLayoutY(450);

        datumAfspraakLabel = new Label("Datum afspraak");
        datumAfspraakLabel.setLayoutX(357);
        datumAfspraakLabel.setLayoutY(500);

        datumAfspraakDatePicker = new DatePicker();
        datumAfspraakDatePicker.setLayoutX(500);
        datumAfspraakDatePicker.setLayoutY(500);

        // Stel de huidige datum in als de standaardwaarde
        datumAfspraakDatePicker.setValue(LocalDate.now());

        // Beperk de datum tot alleen toekomstige data
        datumAfspraakDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        locatieAfspraakLabel = new Label("Locatie afspraak");
        locatieAfspraakLabel.setLayoutX(357);
        locatieAfspraakLabel.setLayoutY(550);

        locatieAfspraakInput = new TextField();
        locatieAfspraakInput.setLayoutX(500);
        locatieAfspraakInput.setLayoutY(550);

        VoegAfspraakToe = new Button("Opslaan");
        VoegAfspraakToe.setLayoutX(500);
        VoegAfspraakToe.setLayoutY(650);
        VoegAfspraakToe.setOnAction(e -> {
            // Voeg je logica toe om de afspraak op te slaan
            voegAfspraakToe();

            Afspraak afspraak = new Afspraak(stage);
        });

        // Populate the ChoiceBoxes with values from the database
        populateZorgVerleners();
        populatePatienten();

        root.getChildren().addAll(terugBtn, patientName, patientChoiceBox, zorgVerlenerNaam, zorgVerlenerChoiceBox,
                tijdAfspraakLabel, tijdAfspraakInput,datumAfspraakLabel, datumAfspraakDatePicker, locatieAfspraakLabel, locatieAfspraakInput, VoegAfspraakToe);

        stage.setTitle("Afspraak maken");
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
        int zorgverlenerId = zorgVerlenerChoiceBox.getValue().getZorgverlenerId();
        int patientId = patientChoiceBox.getValue().getPatientId();
        LocalDate geselecteerdeDatum = datumAfspraakDatePicker.getValue();
        String datum = (geselecteerdeDatum != null) ? geselecteerdeDatum.toString() : ""; // Zet LocalDate naar String

        // Voeg de afspraak toe via de databasehandler
        databaseHandler.toevoegenAfspraak(patientId, zorgverlenerId, Date.valueOf(datum), tijdAfspraakInput.getText(), locatieAfspraakInput.getText());
    }
}
