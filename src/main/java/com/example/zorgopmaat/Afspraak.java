package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Afspraak {

    public Afspraak(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label overzichtLabel, patientLabel, zorgVerlenerLabel, datumLabel, tijdLabel, locatieLabel;
        final Button terugBtn, afspraakToevoegen;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage);});


        overzichtLabel = new Label("Overzicht afspraken");
        overzichtLabel.setLayoutX(558);
        overzichtLabel.setLayoutY(159);

        patientLabel = new Label("Patient");
        patientLabel.setLayoutX(300);
        patientLabel.setLayoutY(250);

        zorgVerlenerLabel = new Label("Zorgverlener");
        zorgVerlenerLabel.setLayoutX(450);
        zorgVerlenerLabel.setLayoutY(250);

        datumLabel = new Label("Datum");
        datumLabel.setLayoutX(600);
        datumLabel.setLayoutY(250);

        tijdLabel = new Label("Tijd");
        tijdLabel.setLayoutX(750);
        tijdLabel.setLayoutY(250);

        locatieLabel = new Label("Locatie");
        locatieLabel.setLayoutX(900);
        locatieLabel.setLayoutY(250);



        afspraakToevoegen = new Button(" Voeg afspraak toe");
        afspraakToevoegen.setLayoutX(110);
        afspraakToevoegen.setLayoutY(620);
        afspraakToevoegen.setOnAction(actionEvent -> { MaakAfspraak maakafspraak = new MaakAfspraak(stage);});

        Button afspraakVerwijderen = new Button(" Verwijder afspraak");
        afspraakVerwijderen.setLayoutX(110);
        afspraakVerwijderen.setLayoutY(660);

        root.getChildren().addAll(terugBtn, overzichtLabel, patientLabel, zorgVerlenerLabel, datumLabel, tijdLabel, locatieLabel, afspraakToevoegen, afspraakVerwijderen);
        stage.setTitle("Zorg op Maat afsprakent");
        stage.setScene(scene);
    }
}

