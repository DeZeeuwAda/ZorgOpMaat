package com.example.zorgopmaat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

    public class ZorgPlanData {
        private final StringProperty patientNaam;
        private final StringProperty zorgverlenerNaam;
        private final StringProperty diagnose;
        private final StringProperty behandelplan;

        public ZorgPlanData(String patientNaam, String zorgverlenerNaam, String diagnose, String behandelplan) {
            this.patientNaam = new SimpleStringProperty(patientNaam);
            this.zorgverlenerNaam = new SimpleStringProperty(zorgverlenerNaam);
            this.diagnose = new SimpleStringProperty(diagnose);
            this.behandelplan = new SimpleStringProperty(behandelplan);
        }

        public String getPatientNaam() {
            return patientNaam.get();
        }

        public StringProperty patientNaamProperty() {
            return patientNaam;
        }

        public String getZorgverlenerNaam() {
            return zorgverlenerNaam.get();
        }

        public StringProperty zorgverlenerNaamProperty() {
            return zorgverlenerNaam;
        }

        public String getDiagnose() {
            return diagnose.get();
        }

        public StringProperty diagnoseProperty() {
            return diagnose;
        }

        public String getBehandelplan() {
            return behandelplan.get();
        }

        public StringProperty behandelplanProperty() {
            return behandelplan;
        }
}
