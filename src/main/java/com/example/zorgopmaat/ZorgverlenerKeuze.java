// ZorgverlenerKeuze.java
package com.example.zorgopmaat;

public class ZorgverlenerKeuze {
    private int zorgverlenerId;
    private String zorgverlenerNaam;

    public ZorgverlenerKeuze(int zorgverlenerId, String zorgverlenerNaam) {
        this.zorgverlenerId = zorgverlenerId;
        this.zorgverlenerNaam = zorgverlenerNaam;
    }

    public int getZorgverlenerId() {
        return zorgverlenerId;
    }

    public String getZorgverlenerNaam() {
        return zorgverlenerNaam;
    }

    @Override
    public String toString() {
        return zorgverlenerNaam; // Dit is wat de ChoiceBox zal weergeven
    }
}
