package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class ZorgPlanToevoegen {

    private Stage stage;
    private Database database;
    private ChoiceBox<PatientKeuze> patientChoiceBox;
    private ChoiceBox<ZorgverlenerKeuze> zorgverlenerChoiceBox;
    private TextField diagnoseTxtField;
    private TextArea behandelplanTxtArea;
    private Button updateBtn;

    public ZorgPlanToevoegen(Stage stage) {
        this.stage = stage;
        this.database = new Database();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");

        patientChoiceBox = new ChoiceBox<>();
        patientChoiceBox.setLayoutX(150);
        patientChoiceBox.setLayoutY(100);
        patientChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateForm(newValue));

        zorgverlenerChoiceBox = new ChoiceBox<>();
        zorgverlenerChoiceBox.setLayoutX(150);
        zorgverlenerChoiceBox.setLayoutY(140);

        Label patientLabel = new Label("Patiënt:");
        patientLabel.setLayoutX(50);
        patientLabel.setLayoutY(100);

        Label zorgverlenerLabel = new Label("Zorgverlener:");
        zorgverlenerLabel.setLayoutX(50);
        zorgverlenerLabel.setLayoutY(140);

        Label diagnoseLabel = new Label("Diagnose:");
        diagnoseLabel.setLayoutX(50);
        diagnoseLabel.setLayoutY(180);

        diagnoseTxtField = new TextField();
        diagnoseTxtField.setLayoutX(150);
        diagnoseTxtField.setLayoutY(180);

        Label behandelplanLabel = new Label("Behandelplan:");
        behandelplanLabel.setLayoutX(50);
        behandelplanLabel.setLayoutY(220);

        behandelplanTxtArea = new TextArea();
        behandelplanTxtArea.setLayoutX(150);
        behandelplanTxtArea.setLayoutY(220);
        behandelplanTxtArea.setMaxSize(300, 100);

        updateBtn = new Button("Zet zorgplan in het systeem");
        updateBtn.setLayoutX(150);
        updateBtn.setLayoutY(360);
        updateBtn.setOnAction(actionEvent ->  {
            updateZorgPlan();
            ZorgPlan zorgPlan = new ZorgPlan(stage);
        });

        Button terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(50);
        terugBtn.setOnAction(actionEvent -> new ZorgPlan(stage));

        root.getChildren().addAll(terugBtn, patientChoiceBox, zorgverlenerChoiceBox, patientLabel, zorgverlenerLabel, diagnoseLabel, diagnoseTxtField, behandelplanLabel, behandelplanTxtArea, updateBtn);

        vulPatientChoiceBox();
        vulZorgverlenerChoiceBox();

        stage.setTitle("Zorg op Maat Zorgplan");
        stage.setScene(scene);
    }

    private void vulPatientChoiceBox() {
        List<PatientKeuze> patienten = database.fetchPatientenFromDatabase();
        patientChoiceBox.getItems().setAll(patienten);
    }

    private void vulZorgverlenerChoiceBox() {
        List<ZorgverlenerKeuze> zorgverleners = database.fetchZorgVerlenersFromDatabase();
        zorgverlenerChoiceBox.getItems().setAll(zorgverleners);
    }

    private void updateForm(PatientKeuze patient) {
        if (patient != null) {
            // Voorbeeld data ophalen, vervang dit door de daadwerkelijke methoden om data op te halen
            diagnoseTxtField.setText("Diagnose van " + patient.getPatientNaam());
            behandelplanTxtArea.setText("Behandelplan voor " + patient.getPatientNaam());
        } else {
            diagnoseTxtField.clear();
            behandelplanTxtArea.clear();
        }
    }

    private void updateZorgPlan() {
        PatientKeuze geselecteerdePatient = patientChoiceBox.getValue();
        ZorgverlenerKeuze geselecteerdeZorgverlener = zorgverlenerChoiceBox.getValue();
        if (geselecteerdePatient != null && geselecteerdeZorgverlener != null) {
            // Logica om het zorgplan voor de geselecteerde patiënt en zorgverlener toe te voegen
            database.toevoegenZorgPlan(geselecteerdePatient.getPatientId(), geselecteerdeZorgverlener.getZorgverlenerId(), diagnoseTxtField.getText(), behandelplanTxtArea.getText());
            System.out.println("Zorgplan toegevoegd voor: " + geselecteerdePatient.getPatientNaam());

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ho, even wachten");
            alert.setHeaderText(null);
            alert.setContentText("Vul eerste alle velden in voordat je een zorgplan toevoegt!");
            alert.showAndWait();

        }
    }
}