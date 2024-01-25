package com.example.zorgopmaat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    // connectie maken met database
    public Database() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://adainforma.tk/bp2_zorgopmaat?" +
                    "user=zorgopmaat&password=527n2!jaE");
            System.out.println(this.connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // functie om patienten toe te voegen, om deze in toevoegenPatient te gebruiken
    public void toevoegenPatient(String naam, String geboortedatum, String contactgegevens) {
        String query = "INSERT INTO `Patient`(`naam`, `geboortedatum`, `contactgegevens`) VALUES ('" + naam + "','" + geboortedatum + "','" + contactgegevens + "')";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Functie om afspraken toe te voegen, om deze in MaakAfspraak te gebruiken
    public void toevoegenAfspraak(int patientid, int zorgverlenerid, Date datum, String tijd, String locatie) {
        String query = "INSERT INTO `Afspraak`(`patientid`, `zorgverlenerid`, `datum`, `tijd`, `locatie`) VALUES (" + patientid + "," + zorgverlenerid + ",'" + datum + "','" + tijd + "','" + locatie + "')";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Functie om zorgverleners op te halen
    public List<ZorgverlenerKeuze> fetchZorgVerlenersFromDatabase() {
        List<ZorgverlenerKeuze> zorgVerleners = new ArrayList<>();

        // JDBC Query
        String sqlQuery = "SELECT zorgverlenerid, naam FROM Zorgverlener";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int zorgVerlenerId = resultSet.getInt("zorgverlenerid");
                String zorgVerlenerNaam = resultSet.getString("naam");
                ZorgverlenerKeuze zorgverlener = new ZorgverlenerKeuze(zorgVerlenerId, zorgVerlenerNaam);
                zorgVerleners.add(zorgverlener);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return zorgVerleners;
    }

    public List<PatientKeuze> fetchPatientenFromDatabase() {
        List<PatientKeuze> patients = new ArrayList<>();

        // JDBC Query
        String sqlQuery = "SELECT patientid, naam FROM Patient";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int patientId = resultSet.getInt("patientid");
                String patientNaam = resultSet.getString("naam");
                PatientKeuze patient = new PatientKeuze(patientId, patientNaam);
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return patients;
    }

    public int getZorgverlenerIdByName(String zorgverlenerNaam) {
        String sqlQuery = "SELECT zorgverlenerid FROM Zorgverlener WHERE naam = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, zorgverlenerNaam);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("zorgverlenerid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        // Return a default value or handle the situation where the zorgverlener is not found
        return -1;
    }

    // Inside the Database class
    public List<AfspraakOverzicht> fetchAfspraakOverzichtFromDatabase() {
        List<AfspraakOverzicht> afspraakOverzichtList = new ArrayList<>();

        // JDBC Query
        String sqlQuery = "SELECT p.naam AS patient, a.tijd, a.locatie, z.naam AS zorgverlener " +
                "FROM Afspraak a " +
                "JOIN Patient p ON a.patientid = p.patientid " +
                "JOIN Zorgverlener z ON a.zorgverlenerid = z.zorgverlenerid";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String patient = resultSet.getString("patient");
                String tijd = resultSet.getString("tijd");
                String locatie = resultSet.getString("locatie");
                String zorgverlener = resultSet.getString("zorgverlener");

                AfspraakOverzicht afspraakOverzicht = new AfspraakOverzicht(patient, tijd, locatie, zorgverlener);
                afspraakOverzichtList.add(afspraakOverzicht);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return afspraakOverzichtList;
    }

}

