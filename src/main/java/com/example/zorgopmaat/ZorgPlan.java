package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ZorgPlan {

    public ZorgPlan(Stage stage) {

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);
        root.setStyle("-fx-background-color: #e4eaea;");


        final Label overzichtLabel,patientLabel, diagnoseLabel,zorgPlanLabel, opmerkingLabel;
        final Button terugBtn;
        final TextField opmerkingTxtField;

        terugBtn = new Button("Terug");
        terugBtn.setLayoutX(100);
        terugBtn.setLayoutY(100);
        terugBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage);});

        overzichtLabel = new Label("Patient en zorgplan");
        overzichtLabel.setLayoutX(558);
        overzichtLabel.setLayoutY(159);

        patientLabel = new Label("Patient");
        patientLabel.setLayoutX(300);
        patientLabel.setLayoutY(250);

        diagnoseLabel = new Label("Diagnose");
        diagnoseLabel.setLayoutX(450);
        diagnoseLabel.setLayoutY(250);

        zorgPlanLabel = new Label("Plan van aanpak");
        zorgPlanLabel.setLayoutX(600);
        zorgPlanLabel.setLayoutY(250);

        opmerkingLabel = new Label("Opmerkingen");
        opmerkingLabel.setLayoutX(600);
        opmerkingLabel.setLayoutY(250);

        opmerkingTxtField = new TextField("Eventuele opmerkingen over of van patient");
        opmerkingTxtField.setLayoutX(600);
        opmerkingTxtField.setLayoutY(250);



        root.getChildren().addAll(terugBtn, overzichtLabel,patientLabel, diagnoseLabel, zorgPlanLabel, opmerkingTxtField);
        stage.setTitle("Zorg op Maat Zorgplan");
        stage.setScene(scene);
    }
}

