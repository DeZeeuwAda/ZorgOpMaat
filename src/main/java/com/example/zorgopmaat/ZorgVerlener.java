package com.example.zorgopmaat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;

public class ZorgVerlener {

    public ZorgVerlener(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");
        Styling.applyStylesheet(scene);

        //tableview maken voor het overzicht van de zorgverleners
        TableView<ZorgVerlenerOverzicht> tableView = createZorgVerlenerOverzichtTableView();
        tableView.setLayoutX(440);
        tableView.setLayoutY(200);
        tableView.setMinWidth(400);
        tableView.setMaxHeight(300);

        //Kolommen toevoegen waarin de informatie wordt weergegeven
        TableColumn<ZorgVerlenerOverzicht, String> naamColumn = new TableColumn<>("Naam");
        TableColumn<ZorgVerlenerOverzicht, String> specialisatieColumn = new TableColumn<>("Specialisatie");
        TableColumn<ZorgVerlenerOverzicht, String> contactgegevensColumn = new TableColumn<>("Contactgegevens");

        Database database = new Database();
        List<ZorgVerlenerOverzicht> zorgVerlenerOverzichtList = database.fetchZorgVerlenerOverzichtFromDatabase();

        ObservableList<ZorgVerlenerOverzicht> zorgVerlenerOverzichtObservableList = FXCollections.observableArrayList(zorgVerlenerOverzichtList);

        tableView.setItems(zorgVerlenerOverzichtObservableList);

        naamColumn.setCellValueFactory(cellData -> cellData.getValue().naamProperty());
        specialisatieColumn.setCellValueFactory(cellData -> cellData.getValue().specialisatieProperty());
        contactgegevensColumn.setCellValueFactory(cellData -> cellData.getValue().contactgegevensProperty());

        // Toevoegen in de tableview.
        tableView.getColumns().addAll(naamColumn, specialisatieColumn, contactgegevensColumn);

        final Label overzichtLabel;
        final Button terugBtn, nieuweZorgVerlBtn, verwijderZorgVerlBtn;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage); });


        overzichtLabel = new Label("Zorgverlener Info");
        overzichtLabel.setLayoutX(558);
        overzichtLabel.setLayoutY(159);

        nieuweZorgVerlBtn = new Button("Voeg zorgverlener toe");
        nieuweZorgVerlBtn.setLayoutX(110);
        nieuweZorgVerlBtn.setLayoutY(620);
        nieuweZorgVerlBtn.setOnAction(actionEvent -> { ZorgverlenerToevoegen zorgVerlenerToevoegen = new ZorgverlenerToevoegen(stage); });

        verwijderZorgVerlBtn = new Button("Verwijder zorgverlener");
        verwijderZorgVerlBtn.setLayoutX(110);
        verwijderZorgVerlBtn.setLayoutY(650);
        verwijderZorgVerlBtn.setOnAction(actionEvent -> {
            ZorgVerlenerOverzicht selectedZorgverlener = tableView.getSelectionModel().getSelectedItem();
            if (selectedZorgverlener != null) {
                // Verwijder de zorgverlener uit de database en update de TableView zonder bevestiging
                database.verwijderZorgVerlener(selectedZorgverlener);
                zorgVerlenerOverzichtObservableList.remove(selectedZorgverlener);
            }
        });




        root.getChildren().addAll(terugBtn, overzichtLabel, nieuweZorgVerlBtn, verwijderZorgVerlBtn, tableView);
        stage.setTitle("Zorg op Maat Zorgverlener");
        stage.setScene(scene);
    }

    private TableView<ZorgVerlenerOverzicht> createZorgVerlenerOverzichtTableView() {
        TableView<ZorgVerlenerOverzicht> tableView = new TableView<>();

        return tableView;
    }
}