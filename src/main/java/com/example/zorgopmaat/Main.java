package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main  extends javafx.application.Application {



    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 832);

        Styling.applyStylesheet(scene);


        final Label welkomLabel;
        final Button klikDoorBtn;

        // Het pad naar je afbeeldingsbestand voor het icoon in de titelbalk
        String iconPath = "/com/example/zorgopmaat/images/ZorgOpMaat.png";
        // Laad het icoon voor de titelbalk
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        // Stel het icoon in voor de titelbalk van het podium (stage)
        stage.getIcons().add(icon);





        Image image = new Image(Main.class.getResource("images/ZorgOpMaat.png").toString());
        ImageView logo = new ImageView(image);
        logo.setImage(image);
        logo.setLayoutX(536);
        logo.setLayoutY(166);


        welkomLabel = new Label("Portaal");
        welkomLabel.setLayoutX(640);
        welkomLabel.setLayoutY(400);

        klikDoorBtn = new Button("Klik hier om door naar het portaal te gaan");
        klikDoorBtn.setLayoutX(550);
        klikDoorBtn.setLayoutY(520);
        klikDoorBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(stage);});

        root.getChildren().addAll(welkomLabel, klikDoorBtn, logo);
        stage.setTitle("Zorg op Maat Portaal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}