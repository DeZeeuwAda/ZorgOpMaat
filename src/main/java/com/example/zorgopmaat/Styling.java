package com.example.zorgopmaat;

import javafx.scene.Scene;

public class Styling {
    public static void applyStylesheet(Scene scene) {
        String cssPath = "/com/example/zorgopmaat/Style/Style.css";
        scene.getStylesheets().add(Styling.class.getResource(cssPath).toExternalForm());
    }
}

