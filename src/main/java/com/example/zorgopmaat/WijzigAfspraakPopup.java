package com.example.zorgopmaat;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class WijzigAfspraakPopup {

    public static void showPopup(Database database, AfspraakData afspraak, ObservableList<AfspraakData> afspraakDataObservableList) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Wijzig Afspraak");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        DatePicker datumPicker = new DatePicker();
        TextField tijdTextField = new TextField();
        TextField locatieTextField = new TextField();

        Label datumLabel = new Label("Datum:");
        Label tijdLabel = new Label("Tijd:");
        Label locatieLabel = new Label("Locatie:");

        datumPicker.setValue(LocalDate.parse(afspraak.getDatum()));
        tijdTextField.setText(afspraak.getTijd());
        locatieTextField.setText(afspraak.getLocatie());

        Button wijzigAfspraakButton = new Button("Wijzig Afspraak");
        wijzigAfspraakButton.setOnAction(event -> {
            LocalDate selectedDatum = datumPicker.getValue();
            String selectedTijd = tijdTextField.getText();
            String selectedLocatie = locatieTextField.getText();

            if (selectedDatum != null && !selectedTijd.isEmpty()) {
                // Update de afspraak in de database
                AfspraakData nieuweAfspraak = new AfspraakData(
                        afspraak.getPatient(),
                        afspraak.getZorgverlener(),
                        selectedDatum.toString(),
                        selectedTijd,
                        selectedLocatie
                );

                database.wijzigAfspraak(afspraak, nieuweAfspraak);
                afspraakDataObservableList.set(afspraakDataObservableList.indexOf(afspraak), nieuweAfspraak);

                popupStage.close();
            } else {
                // Toon een waarschuwing als niet alle velden zijn ingevuld
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Waarschuwing");
                alert.setHeaderText(null);
                alert.setContentText("Vul alle velden in.");
                alert.showAndWait();
            }
        });

        vbox.getChildren().addAll(datumLabel, datumPicker, tijdLabel, tijdTextField, locatieLabel, locatieTextField, wijzigAfspraakButton);

        Scene scene = new Scene(vbox, 300, 250);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}