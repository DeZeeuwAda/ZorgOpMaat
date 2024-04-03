package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ZorgPlan {

    private Stage stage;
    private Database database;
    private TableView<ZorgPlanData> tableView;

    public ZorgPlan(Stage stage) {
        this.stage = stage;
        this.database = new Database();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        tableView = new TableView<>();
        tableView.setLayoutX(50);
        tableView.setLayoutY(100);
        tableView.setPrefSize(1180, 600);
        setupZorgPlanTableView();

        Button toevoegenBtn = new Button("Zorgplan Toevoegen");
        toevoegenBtn.setLayoutX(1050);
        toevoegenBtn.setLayoutY(50);
        toevoegenBtn.setOnAction(actionEvent -> new ZorgPlanToevoegen(stage));

        Button terugBtn = new Button("Terug");
        terugBtn.setLayoutX(50);
        terugBtn.setLayoutY(50);
        terugBtn.setOnAction(actionEvent -> new Overzicht(stage));

        root.getChildren().addAll(terugBtn, toevoegenBtn, tableView);

        stage.setTitle("Zorg op Maat - Zorgplan Overzicht");
        stage.setScene(scene);
        stage.show();
    }

    //Instellen van de rijen en kolommen van de tableview
    private void setupZorgPlanTableView() {
        TableColumn<ZorgPlanData, String> patientColumn = new TableColumn<>("Patiënt");
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patientNaam"));
        patientColumn.setMinWidth(200);

        TableColumn<ZorgPlanData, String> zorgverlenerColumn = new TableColumn<>("Zorgverlener");
        zorgverlenerColumn.setCellValueFactory(new PropertyValueFactory<>("zorgverlenerNaam"));
        zorgverlenerColumn.setMinWidth(200);

        TableColumn<ZorgPlanData, String> diagnoseColumn = new TableColumn<>("Diagnose");
        diagnoseColumn.setCellValueFactory(new PropertyValueFactory<>("diagnose"));
        diagnoseColumn.setMinWidth(200);

        TableColumn<ZorgPlanData, String> behandelplanColumn = new TableColumn<>("Behandelplan");
        behandelplanColumn.setCellValueFactory(new PropertyValueFactory<>("behandelplan"));
        behandelplanColumn.setMinWidth(200);


        tableView.getColumns().clear();
        tableView.getColumns().addAll(patientColumn, zorgverlenerColumn, diagnoseColumn, behandelplanColumn);

        List<ZorgPlanData> zorgPlannen = database.fetchZorgPlannenFromDatabase();
        ObservableList<ZorgPlanData> data = FXCollections.observableArrayList(zorgPlannen);
        tableView.setItems(data);
    }

}