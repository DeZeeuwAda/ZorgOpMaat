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
    private TextField naamPatientInput, tijdAfspraakInput, datumAfspraakInput, locatieAfspraakInput;
    private Button terugBtn, voegAfspraakToeBtn;

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

        naamPatientInput = new TextField();
        naamPatientInput.setLayoutX(500);
        naamPatientInput.setLayoutY(300);

        zorgVerlenerChoiceBox = new ChoiceBox<>();
        zorgVerlenerChoiceBox.setLayoutX(500);
        zorgVerlenerChoiceBox.setLayoutY(350);

        tijdAfspraakInput = new TextField();
        tijdAfspraakInput.setLayoutX(500);
        tijdAfspraakInput.setLayoutY(400);

        datumAfspraakInput = new TextField();
        datumAfspraakInput.setLayoutX(500);
        datumAfspraakInput.setLayoutY(450);

        locatieAfspraakInput = new TextField();
        locatieAfspraakInput.setLayoutX(500);
        locatieAfspraakInput.setLayoutY(500);

        voegAfspraakToeBtn = new Button("Opslaan");
        voegAfspraakToeBtn.setLayoutX(500);
        voegAfspraakToeBtn.setLayoutY(650);
        voegAfspraakToeBtn.setOnAction(e -> {
            // Voeg je logica toe om de afspraak op te slaan
            voegAfspraakToe();
        });

        // Populate the ChoiceBox with values from the database
        populateZorgVerleners();

        root.getChildren().addAll(terugBtn, naamPatientInput, zorgVerlenerChoiceBox, tijdAfspraakInput,
                datumAfspraakInput, locatieAfspraakInput, voegAfspraakToeBtn);

        stage.setScene(scene);
    }

    private void populateZorgVerleners() {
        // Fetch Zorg Verlener namen en id's from the database and populate the ChoiceBox
        List<ZorgverlenerKeuze> zorgVerleners = databaseHandler.fetchZorgVerlenersFromDatabase();
        ObservableList<ZorgverlenerKeuze> zorgVerlenersList = FXCollections.observableArrayList(zorgVerleners);
        zorgVerlenerChoiceBox.setItems(zorgVerlenersList);
    }

    private void voegAfspraakToe() {
        // Ophalen van de benodigde gegevens
        String naamPatient = naamPatientInput.getText();
        ZorgverlenerKeuze selectedZorgverlener = zorgVerlenerChoiceBox.getValue();
        int zorgverlenerId = selectedZorgverlener.getZorgverlenerId();
        String datum = datumAfspraakInput.getText();
        String tijd = tijdAfspraakInput.getText();
        String locatie = locatieAfspraakInput.getText();

        // Voeg de afspraak toe via de databasehandler
        databaseHandler.toevoegenAfspraak(naamPatient, zorgverlenerId, datum, tijd, locatie);

        // Voeg hier eventueel verdere logica toe na het toevoegen van de afspraak
    }
}

//V1
