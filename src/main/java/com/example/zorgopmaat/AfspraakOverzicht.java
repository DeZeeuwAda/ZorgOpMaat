package com.example.zorgopmaat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AfspraakOverzicht {
    private final StringProperty patient;
    private final StringProperty tijd;
    private final StringProperty locatie;
    private final StringProperty zorgverlener;

    public AfspraakOverzicht(String patient, String tijd, String locatie, String zorgverlener) {
        this.patient = new SimpleStringProperty(patient);
        this.tijd = new SimpleStringProperty(tijd);
        this.locatie = new SimpleStringProperty(locatie);
        this.zorgverlener = new SimpleStringProperty(zorgverlener);
    }

    // Getters voor het overzicht
    public String getPatient() {
        return patient.get();
    }

    public StringProperty patientProperty() {
        return patient;
    }

    public String getTijd() {
        return tijd.get();
    }

    public StringProperty tijdProperty() {
        return tijd;
    }

    public String getLocatie() {
        return locatie.get();
    }

    public StringProperty locatieProperty() {
        return locatie;
    }

    public String getZorgverlener() {
        return zorgverlener.get();
    }

    public StringProperty zorgverlenerProperty() {
        return zorgverlener;
    }
}