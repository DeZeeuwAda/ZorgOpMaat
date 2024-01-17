// ZorgverlenerKeuze.java
package com.example.zorgopmaat;

public class PatientKeuze {
    private int patientId;
    private String patientNaam;

    public PatientKeuze(int patientId, String patientNaam) {
        this.patientId = patientId;
        this.patientNaam = patientNaam;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientNaam() {
        return patientNaam;
    }

    @Override
    public String toString() {
        return patientNaam;
    }
}
