package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class Overzicht {

    public Overzicht(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        Styling.applyStylesheet(scene);

        final Label planningLabel;
        final Button patientBtn, afspraakBtn, zorgPlanBtn, zorgVerlenerBtn;

        Image image = new Image(Main.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(515);
        logo.setLayoutY(27);

        planningLabel = new Label("Planning komende tijd");
        planningLabel.setLayoutX(835);
        planningLabel.setLayoutY(245);

        // Correcte aanroep van de methode
        TableView<AfspraakOverzicht> tableView = createAfspraakOverzichtTableView();
        tableView.setLayoutX(835);
        tableView.setLayoutY(280);

        patientBtn = new Button("Patienten");
        patientBtn.setMinWidth(150);
        patientBtn.setLayoutX(160);
        patientBtn.setLayoutY(300);
        patientBtn.setOnAction(actionEvent -> {
            Patient patient = new Patient(stage);
        });

        afspraakBtn = new Button("Afspraak");
        afspraakBtn.setMinWidth(150);
        afspraakBtn.setLayoutX(160);
        afspraakBtn.setLayoutY(350);
        afspraakBtn.setOnAction(actionEvent -> {
            Afspraak afspraak = new Afspraak(stage);
        });

        zorgPlanBtn = new Button("Zorgplan");
        zorgPlanBtn.setMinWidth(150);
        zorgPlanBtn.setLayoutX(160);
        zorgPlanBtn.setLayoutY(400);
        zorgPlanBtn.setOnAction(actionEvent -> {
            ZorgPlan zorgplan = new ZorgPlan(stage);
        });

        zorgVerlenerBtn = new Button("Zorgverlener");
        zorgVerlenerBtn.setLayoutX(160);
        zorgVerlenerBtn.setLayoutY(450);
        zorgVerlenerBtn.setMinWidth(150);
        zorgVerlenerBtn.setOnAction(actionEvent -> {
            ZorgVerlener zorgverlener = new ZorgVerlener(stage);
        });


        // Toevoegen van de tableview en invoeren van alle gegevens
        TableColumn<AfspraakOverzicht, String> patientColumn = new TableColumn<>("Patient");
        TableColumn<AfspraakOverzicht, String> tijdColumn = new TableColumn<>("Tijd");
        TableColumn<AfspraakOverzicht, String> locatieColumn = new TableColumn<>("Locatie");
        TableColumn<AfspraakOverzicht, String> zorgverlenerColumn = new TableColumn<>("Zorgverlener");

        tableView.getColumns().addAll(patientColumn, tijdColumn, locatieColumn, zorgverlenerColumn);

        // Haalt de data uit de database door database klasse te gebruiken
        Database database = new Database();
        List<AfspraakOverzicht> afspraakOverzichtList = database.fetchAfspraakOverzichtFromDatabase();

        ObservableList<AfspraakOverzicht> afspraakOverzichtObservableList = FXCollections.observableArrayList(afspraakOverzichtList);

        tableView.setItems(afspraakOverzichtObservableList);

        patientColumn.setCellValueFactory(cellData -> cellData.getValue().patientProperty());
        tijdColumn.setCellValueFactory(cellData -> cellData.getValue().tijdProperty());
        locatieColumn.setCellValueFactory(cellData -> cellData.getValue().locatieProperty());
        zorgverlenerColumn.setCellValueFactory(cellData -> cellData.getValue().zorgverlenerProperty());

        root.getChildren().addAll(logo, planningLabel, tableView, patientBtn, afspraakBtn, zorgPlanBtn, zorgVerlenerBtn);
        stage.setTitle("Zorg op Maat overzicht");
        stage.setScene(scene);
    }

    // Methode om de TableView te maken met het overzicht wat ik nodig heb
    private TableView<AfspraakOverzicht> createAfspraakOverzichtTableView() {
        TableView<AfspraakOverzicht> tableView = new TableView<>();
        TableColumn<AfspraakOverzicht, String> patientColumn = new TableColumn<>("Patient");
        TableColumn<AfspraakOverzicht, String> tijdColumn = new TableColumn<>("Tijd");



        return tableView;
    }
}