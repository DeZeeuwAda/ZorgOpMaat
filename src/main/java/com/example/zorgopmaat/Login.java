package com.example.zorgopmaat;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.ResultSet;


public class Login {

        public Login(Stage Stage) {

            Pane root = new Pane();
            Scene scene = new Scene(root, 1280, 832);
            root.setStyle("-fx-background-color: #e4eaea;");

            final Label usernamelabel, passwordlabel;
            final TextField usernametxt;
            final PasswordField passwordtxt;
            final Button loginBtn;

            usernamelabel = new Label("Username");
            usernamelabel.setLayoutX(592);
            usernamelabel.setLayoutY(369);
            usernametxt = new TextField();
            usernametxt.setLayoutX(592);
            usernametxt.setLayoutY(416);
            passwordlabel = new Label("Password");
            passwordlabel.setLayoutX(592);
            passwordlabel.setLayoutY(532);
            passwordtxt = new PasswordField();
            passwordtxt.setLayoutY(571);

            loginBtn = new Button("Login");
            loginBtn.setLayoutX(592);
            loginBtn.setLayoutY(671);
            loginBtn.setOnAction(actionEvent -> { Overzicht overzicht = new Overzicht(Stage);});


//            loginBtn.setOnAction(actionEvent -> {
//                try (Database db = new Database()) {
//                    ResultSet passwordCheck = db.selectPassword(usernametxt.getText());
//                    if (passwordCheck.next()) {
//                        if (passwordtxt.getText().equals(passwordCheck.getString("Password"))) {
//                            // Toon het overzicht op het scherm of voer verdere acties uit
//                            Overzicht overzicht = new Overzicht(Stage);
//                        } else {
//                            // Toon foutmelding op het scherm of voer verdere acties uit
//                            Foutmelding foutmelding = new Foutmelding();
//                        }
//                    }
//                } catch (SQLException e) {
//                    // Behandel database-gerelateerde uitzonderingen hier
//                    e.printStackTrace(); // Gebruik een logger in een productieomgeving
//                } catch (Exception e) {
//                    // Behandel andere uitzonderingen hier
//                    e.printStackTrace(); // Gebruik een logger in een productieomgeving
//                }
//            });


            root.getChildren().addAll(usernamelabel, usernametxt, passwordlabel, passwordtxt, loginBtn);
            Stage.setScene(scene);
        }
    }

