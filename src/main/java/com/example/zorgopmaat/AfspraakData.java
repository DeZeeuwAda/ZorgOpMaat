package com.example.zorgopmaat;

public class AfspraakData {
    private final String patient;
    private final String zorgverlener;
    private final String datum;
    private final String tijd;
    private final String locatie;

    public AfspraakData(String patient, String zorgverlener, String datum, String tijd, String locatie) {
        this.patient = patient;
        this.zorgverlener = zorgverlener;
        this.datum = datum;
        this.tijd = tijd;
        this.locatie = locatie;
    }

    public String getPatient() {
        return patient;
    }

    public String getZorgverlener() {
        return zorgverlener;
    }

    public String getDatum() {
        return datum;
    }

    public String getTijd() {
        return tijd;
    }

    public String getLocatie() {
        return locatie;
    }

    @Override
    public String toString() {
        return "AfspraakData{" +
                "patient='" + patient + '\'' +
                ", zorgverlener='" + zorgverlener + '\'' +
                ", datum='" + datum + '\'' +
                ", tijd='" + tijd + '\'' +
                ", locatie='" + locatie + '\'' +
                '}';
    }
}
