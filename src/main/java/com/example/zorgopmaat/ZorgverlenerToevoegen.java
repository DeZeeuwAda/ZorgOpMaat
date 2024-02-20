package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ZorgverlenerToevoegen {

    public ZorgverlenerToevoegen(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label zorgVerlenerNaam, zorgVerlenerSpecialisatie, zorgVerlenerContactgegevens;
        final TextField zorgVerlenerNaamInput, zorgVerlenerContactInput, zorgVerlenerSpecialisatieInput;
        final Button terugBtn,voegZorgVerlenerToe;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { ZorgVerlener zorgVerlener = new ZorgVerlener(stage);});

        zorgVerlenerNaam = new Label("Naam zorgverlener");
        zorgVerlenerNaam.setLayoutX(357);
        zorgVerlenerNaam.setLayoutY(375);

        zorgVerlenerNaamInput = new TextField();
        zorgVerlenerNaamInput.setLayoutX(557);
        zorgVerlenerNaamInput.setLayoutY(375);

        zorgVerlenerSpecialisatie = new Label("Specialisatie zorgverlener");
        zorgVerlenerSpecialisatie.setLayoutX(357);
        zorgVerlenerSpecialisatie.setLayoutY(427);

        zorgVerlenerSpecialisatieInput = new TextField();
        zorgVerlenerSpecialisatieInput.setLayoutX(557);
        zorgVerlenerSpecialisatieInput.setLayoutY(427);

        zorgVerlenerContactgegevens = new Label("Contactgegevens patient");
        zorgVerlenerContactgegevens.setLayoutX(357);
        zorgVerlenerContactgegevens.setLayoutY(479);

        zorgVerlenerContactInput = new TextField();
        zorgVerlenerContactInput.setLayoutX(557);
        zorgVerlenerContactInput.setLayoutY(479);

        voegZorgVerlenerToe = new Button("Opslaan");
        voegZorgVerlenerToe.setLayoutX(560);
        voegZorgVerlenerToe.setLayoutY(630);

        voegZorgVerlenerToe.setOnAction(e->{
            String Naam = zorgVerlenerNaamInput.getText();
            String Specialisatie = zorgVerlenerSpecialisatieInput.getText();
            String Contactgegevens = zorgVerlenerContactInput.getText();

            if (Naam.isEmpty() || Specialisatie.isEmpty() || Contactgegevens.isEmpty()) {
                // Toon een melding dat alle velden moeten worden ingevuld
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Oeps!");
                alert.setHeaderText(null);
                alert.setContentText("Vul alle gegevens in van de zorgverlener");
                alert.showAndWait();
            } else {
                // Alle velden zijn ingevuld, voeg zorgverlener toe aan database
                Database database = new Database();
                database.toevoegenZorgVerlener(Naam, Specialisatie, Contactgegevens);
                ZorgVerlener zorgverlener = new ZorgVerlener(stage);
            }
        });


        root.getChildren().addAll(terugBtn, zorgVerlenerNaam, zorgVerlenerNaamInput,zorgVerlenerSpecialisatie, zorgVerlenerSpecialisatieInput, zorgVerlenerContactgegevens, zorgVerlenerContactInput, voegZorgVerlenerToe);
        stage.setTitle("Zorg op Maat zorgverlener toevoegen");
        stage.setScene(scene);
    }


}
