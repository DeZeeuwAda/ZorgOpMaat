package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class Afspraak {

    private final Database database;
    private final TableView<AfspraakData> tableView;

    public Afspraak(Stage stage) {
        this.database = new Database();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");
        Styling.applyStylesheet(scene);

        tableView = new TableView<>();
        tableView.setLayoutX(300);
        tableView.setLayoutY(280);
        tableView.setPrefSize(600, 400);

        TableColumn<AfspraakData, String> patientColumn = new TableColumn<>("Patient");
        TableColumn<AfspraakData, String> zorgVerlenerColumn = new TableColumn<>("Zorgverlener");
        TableColumn<AfspraakData, String> datumColumn = new TableColumn<>("Datum");
        TableColumn<AfspraakData, String> tijdColumn = new TableColumn<>("Tijd");
        TableColumn<AfspraakData, String> locatieColumn = new TableColumn<>("Locatie");

        tableView.getColumns().addAll(patientColumn, zorgVerlenerColumn, datumColumn, tijdColumn, locatieColumn);

        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient"));
        patientColumn.setPrefWidth(150);

        zorgVerlenerColumn.setCellValueFactory(new PropertyValueFactory<>("zorgverlener"));
        zorgVerlenerColumn.setPrefWidth(150);

        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumColumn.setPrefWidth(150);

        tijdColumn.setCellValueFactory(new PropertyValueFactory<>("tijd"));
        tijdColumn.setPrefWidth(50);

        locatieColumn.setCellValueFactory(new PropertyValueFactory<>("locatie"));
        locatieColumn.setPrefWidth(100);

        List<AfspraakData> afspraakDataList = database.fetchAfspraakDataFromDatabase();
        ObservableList<AfspraakData> afspraakDataObservableList = FXCollections.observableArrayList(afspraakDataList);
        tableView.setItems(afspraakDataObservableList);

        final Label overzichtLabel;
        final Button terugBtn, afspraakToevoegen, afspraakVerwijderen, afspraakWijzigen;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> {
            Overzicht overzicht = new Overzicht(stage);
        });

        overzichtLabel = new Label("Overzicht afspraken");
        overzichtLabel.setLayoutX(558);
        overzichtLabel.setLayoutY(159);

        afspraakToevoegen = new Button("Voeg afspraak toe");
        afspraakToevoegen.setLayoutX(110);
        afspraakToevoegen.setLayoutY(620);
        afspraakToevoegen.setOnAction(actionEvent -> {
            MaakAfspraak maakafspraak = new MaakAfspraak(stage);
        });

        afspraakVerwijderen = new Button("Verwijder afspraak");
        afspraakVerwijderen.setLayoutX(110);
        afspraakVerwijderen.setLayoutY(660);
        afspraakVerwijderen.setOnAction(actionEvent -> {
            AfspraakData selectedAfspraak = tableView.getSelectionModel().getSelectedItem();
            if (selectedAfspraak != null) {
                // Verwijder de afspraak uit de database en update de TableView zonder bevestiging
                database.verwijderAfspraak(selectedAfspraak);
                afspraakDataObservableList.remove(selectedAfspraak);
            }
        });

        afspraakWijzigen = new Button("Wijzig afspraak");
        afspraakWijzigen.setLayoutX(110);
        afspraakWijzigen.setLayoutY(700);
        afspraakWijzigen.setOnAction(actionEvent -> {
            AfspraakData selectedAfspraak = tableView.getSelectionModel().getSelectedItem();
            if (selectedAfspraak != null) {
                WijzigAfspraakPopup.showPopup(database, selectedAfspraak, afspraakDataObservableList);
            }
        });

        root.getChildren().addAll(terugBtn, overzichtLabel, afspraakToevoegen, afspraakVerwijderen, afspraakWijzigen, tableView);
        stage.setTitle("Zorg op Maat afspraken");
        stage.setScene(scene);
    }
}