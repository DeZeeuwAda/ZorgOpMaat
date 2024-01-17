package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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

        planningLabel = new Label("Planning voor de dag");
        planningLabel.setLayoutX(835);
        planningLabel.setLayoutY(245);

        patientBtn = new Button("Patienten");
        patientBtn.setMinWidth(150);
        patientBtn.setLayoutX(160);
        patientBtn.setLayoutY(300);
        patientBtn.setOnAction(actionEvent -> { Patient patient = new Patient(stage);});

        afspraakBtn = new Button("Afspraak");
        afspraakBtn.setMinWidth(150);
        afspraakBtn.setLayoutX(160);
        afspraakBtn.setLayoutY(350);
        afspraakBtn.setOnAction(actionEvent -> { Afspraak afspraak = new Afspraak(stage);});

        zorgPlanBtn = new Button("Zorgplan");
        zorgPlanBtn.setMinWidth(150);
        zorgPlanBtn.setLayoutX(160);
        zorgPlanBtn.setLayoutY(400);
        zorgPlanBtn.setOnAction(actionEvent -> { ZorgPlan zorgplan = new ZorgPlan(stage);});

        zorgVerlenerBtn = new Button("Zorgverlener");
        zorgVerlenerBtn.setMinWidth(150);
        zorgVerlenerBtn.setLayoutX(160);
        zorgVerlenerBtn.setLayoutY(450);
        zorgVerlenerBtn.setOnAction(actionEvent -> { ZorgVerlener zorgverlener = new ZorgVerlener(stage);});




        root.getChildren().addAll(logo, planningLabel,patientBtn, afspraakBtn, zorgPlanBtn, zorgVerlenerBtn);
        stage.setTitle("Zorg op Maat overzicht");
        stage.setScene(scene);

    }
}

