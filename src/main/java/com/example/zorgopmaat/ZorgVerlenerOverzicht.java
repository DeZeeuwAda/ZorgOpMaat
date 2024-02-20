package com.example.zorgopmaat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ZorgVerlenerOverzicht {
    private final StringProperty naam;
    private final StringProperty specialisatie;
    private final StringProperty contactgegevens;

    public ZorgVerlenerOverzicht(String naam, String specialisatie, String contactgegevens) {
        this.naam = new SimpleStringProperty(naam);
        this.specialisatie = new SimpleStringProperty(specialisatie);
        this.contactgegevens = new SimpleStringProperty(contactgegevens);
    }

    public String getNaam() {
        return naam.get();
    }

    public StringProperty naamProperty() {
        return naam;
    }

    public String getSpecialisatie() {
        return specialisatie.get();
    }

    public StringProperty specialisatieProperty() {
        return specialisatie;
    }

    public String getContactgegevens() {
        return contactgegevens.get();
    }

    public StringProperty contactgegevensProperty() {
        return contactgegevens;
    }
}
