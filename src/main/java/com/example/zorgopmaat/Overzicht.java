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

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

public class Overzicht {

    public Overzicht(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        final Label planningLabel;
        final Button patientBtn, afspraakBtn, zorgPlanBtn, zorgVerlenerBtn;

        Image image = new Image(HelloApplication.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(515);
        logo.setLayoutY(27);

        planningLabel = new Label("Planning komende tijd");
        planningLabel.setLayoutX(835);
        planningLabel.setLayoutY(245);

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
        zorgVerlenerBtn.setMinWidth(150);
        zorgVerlenerBtn.setLayoutX(160);
        zorgVerlenerBtn.setLayoutY(450);
        zorgVerlenerBtn.setOnAction(actionEvent -> {
            ZorgVerlener zorgverlener = new ZorgVerlener(stage);
        });

        root.getChildren().addAll(logo, planningLabel, patientBtn, afspraakBtn, zorgPlanBtn, zorgVerlenerBtn, tableView);
        stage.setTitle("Zorg op Maat overzicht");
        stage.setScene(scene);
    }

    private TableView<AfspraakOverzicht> createAfspraakOverzichtTableView() {
        TableView<AfspraakOverzicht> tableView = new TableView<>();
        TableColumn<AfspraakOverzicht, String> patientColumn = new TableColumn<>("Patient");
        TableColumn<AfspraakOverzicht, String> tijdColumn = new TableColumn<>("Tijd");
        TableColumn<AfspraakOverzicht, String> locatieColumn = new TableColumn<>("Locatie");
        TableColumn<AfspraakOverzicht, String> zorgverlenerColumn = new TableColumn<>("Zorgverlener");

        tableView.getColumns().addAll(patientColumn, tijdColumn, locatieColumn, zorgverlenerColumn);

        // Fetch data from the database using Database class
        Database database = new Database();
        List<AfspraakOverzicht> afspraakOverzichtList = database.fetchAfspraakOverzichtFromDatabase();

        ObservableList<AfspraakOverzicht> afspraakOverzichtObservableList = FXCollections.observableArrayList(afspraakOverzichtList);

        // Set a custom comparator for the tijdColumn
        tijdColumn.setComparator(Comparator.comparing(time -> {
            try {
                return LocalTime.parse(time);
            } catch (DateTimeParseException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
                return LocalTime.MIN; // Default to the minimum time in case of an error
            }
        }));

        tableView.setItems(afspraakOverzichtObservableList);

        patientColumn.setCellValueFactory(cellData -> cellData.getValue().patientProperty());
        tijdColumn.setCellValueFactory(cellData -> cellData.getValue().tijdProperty());
        locatieColumn.setCellValueFactory(cellData -> cellData.getValue().locatieProperty());
        zorgverlenerColumn.setCellValueFactory(cellData -> cellData.getValue().zorgverlenerProperty());

        return tableView;
    }

}


