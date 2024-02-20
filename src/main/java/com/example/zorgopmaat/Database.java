
package com.example.zorgopmaat;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {
    private Connection connection;

    public Database() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://adainforma.tk/bp2_zorgopmaat?" +
                    "user=zorgopmaat&password=527n2!jaE");
            System.out.println(this.connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void toevoegenPatient(String naam, String geboortedatum, String contactgegevens) {
        String query = "INSERT INTO `Patient`(`naam`, `geboortedatum`, `contactgegevens`) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, naam);
            statement.setString(2, geboortedatum);
            statement.setString(3, contactgegevens);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toevoegenAfspraak(int patientid, int zorgverlenerid, Date datum, String tijd, String locatie) {
        String query = "INSERT INTO `Afspraak`(`patientid`, `zorgverlenerid`, `datum`, `tijd`, `locatie`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, patientid);
            statement.setInt(2, zorgverlenerid);
            statement.setDate(3, datum);
            statement.setString(4, tijd);
            statement.setString(5, locatie);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // toevoegen van zorgverleners
    public void toevoegenZorgVerlener(String naam, String specialisatie, String contactgegevens) {
        String query = "INSERT INTO `Zorgverlener`(`naam`, `specialisatie`, `contactgegevens`) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, naam);
            statement.setString(2, specialisatie);
            statement.setString(3, contactgegevens);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ZorgverlenerKeuze> fetchZorgVerlenersFromDatabase() {
        List<ZorgverlenerKeuze> zorgVerleners = new ArrayList<>();
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
            e.printStackTrace();
        }


        return zorgVerleners;
    }

    public List<ZorgVerlenerOverzicht> fetchZorgVerlenerOverzichtFromDatabase() {
        List<ZorgVerlenerOverzicht> zorgVerlenerOverzichtList = new ArrayList<>();
        String ZorgVerlenerOverzichtQuery = "SELECT naam, specialisatie, contactgegevens FROM Zorgverlener";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ZorgVerlenerOverzichtQuery)) {

            while (resultSet.next()) {
                String naam = resultSet.getString("naam");
                String specialisatie = resultSet.getString("specialisatie");
                String contactgegevens = resultSet.getString("contactgegevens");

                ZorgVerlenerOverzicht zorgVerlenerOverzicht = new ZorgVerlenerOverzicht(naam, specialisatie, contactgegevens);
                zorgVerlenerOverzichtList.add(zorgVerlenerOverzicht);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zorgVerlenerOverzichtList;
    }



    public List<PatientKeuze> fetchPatientenFromDatabase() {
        List<PatientKeuze> patients = new ArrayList<>();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return -1;
    }

    public int getPatientIdByName(String patientNaam) {
        String sqlQuery = "SELECT patientid FROM Patient WHERE naam = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, patientNaam);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("patientid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Vult de tableview met de gegevens uit de database voor de afspraken
    public List<AfspraakOverzicht> fetchAfspraakOverzichtFromDatabase() {
        List<AfspraakOverzicht> afspraakOverzichtList = new ArrayList<>();
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
            e.printStackTrace();
        }

        return afspraakOverzichtList;
    }

    //Afspraken ophalen uit de database
    public List<AfspraakData> fetchAfspraakDataFromDatabase() {
        List<AfspraakData> afspraakDataList = new ArrayList<>();
        String sqlQuery = "SELECT p.naam AS patient, z.naam AS zorgverlener, a.datum, a.tijd, a.locatie " +
                "FROM Afspraak a " +
                "JOIN Patient p ON a.patientid = p.patientid " +
                "JOIN Zorgverlener z ON a.zorgverlenerid = z.zorgverlenerid";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                String patient = resultSet.getString("patient");
                String zorgverlener = resultSet.getString("zorgverlener");
                String datum = resultSet.getString("datum");
                String tijd = resultSet.getString("tijd");
                String locatie = resultSet.getString("locatie");

                AfspraakData afspraakData = new AfspraakData(patient, zorgverlener, datum, tijd, locatie);
                afspraakDataList.add(afspraakData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return afspraakDataList;
    }

    //Wijzigen van de afspraak, waarbij de oude gegevens worden vervangen met de nieuwe.
    public void wijzigAfspraak(AfspraakData oudeAfspraak, AfspraakData nieuweAfspraak) {
        if (bevestigActie("Wijzigen")) {
            String updateQuery = "UPDATE Afspraak SET datum=?, tijd=?, locatie=? WHERE afspraakid=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDate(1, Date.valueOf(LocalDate.parse(nieuweAfspraak.getDatum())));
                preparedStatement.setString(2, nieuweAfspraak.getTijd());
                preparedStatement.setString(3, nieuweAfspraak.getLocatie());
                preparedStatement.setInt(4, getAfspraakId(oudeAfspraak));

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Het verwijderen van de afspraak in de afspraakdatabase.
    public void verwijderAfspraak(AfspraakData afspraak) {
        if (bevestigActie("Verwijderen")) {
            String deleteQuery = "DELETE FROM Afspraak WHERE afspraakid=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, getAfspraakId(afspraak));
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Verwijderen van een zorgverlener binnen de zorgverlener database
    public void verwijderZorgVerlener(ZorgVerlenerOverzicht zorgverlener) {
        if (bevestigActie("Verwijderen")) {
            String deleteQuery = "DELETE FROM Zorgverlener WHERE naam=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, zorgverlener.getNaam());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private int getAfspraakId(AfspraakData afspraak) {
        String selectQuery = "SELECT afspraakid FROM Afspraak WHERE patientid=? AND zorgverlenerid=? AND datum=? AND tijd=? AND locatie=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, getPatientIdByName(afspraak.getPatient()));
            preparedStatement.setInt(2, getZorgverlenerIdByName(afspraak.getZorgverlener()));
            preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(afspraak.getDatum())));
            preparedStatement.setString(4, afspraak.getTijd());
            preparedStatement.setString(5, afspraak.getLocatie());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("afspraakid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private boolean bevestigActie(String actie) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bevestig " + actie);
        alert.setHeaderText(null);
        alert.setContentText("Weet je zeker dat je wilt " + actie.toLowerCase() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
