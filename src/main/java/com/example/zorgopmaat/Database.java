// Database.java
package com.example.zorgopmaat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

    public Database() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://adainforma.tk/bp5_ikigai?" +
                    "user=ikigai&password=1@8a7L5jm");
            System.out.println(this.connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void toevoegenPatient(String naam, String geboortedatum, String contactgegevens) {
        String query = "INSERT INTO `Patient`(`naam`, `geboortedatum`, `contactgegevens`) VALUES ('" + naam + "','" + geboortedatum + "','" + contactgegevens + "')";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toevoegenAfspraak(String naamPatient, int zorgverlenerId, String datum, String tijd, String locatie) {
        String query = "INSERT INTO `Afspraak`(`patientid`, `zorgverlenerid`, `datum`, `tijd`, `locatie`) VALUES ((SELECT patientid FROM Patient WHERE naam = '" + naamPatient + "'),'" + zorgverlenerId + "','" + datum + "','" + tijd + "','" + locatie + "')";
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Database.java (vervolg)
    public List<ZorgverlenerKeuze> fetchZorgVerlenersFromDatabase() {
        List<ZorgverlenerKeuze> zorgVerleners = new ArrayList<>();

        // JDBC Query
        String sqlQuery = "SELECT zorgverlenerid, naam FROM Zorgverlener";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {
                int zorgverlenerId = resultSet.getInt("zorgverlenerid");
                String zorgverlenerNaam = resultSet.getString("naam");
                ZorgverlenerKeuze zorgverlener = new ZorgverlenerKeuze(zorgverlenerId, zorgverlenerNaam);
                zorgVerleners.add(zorgverlener);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zorgVerleners;
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
}

