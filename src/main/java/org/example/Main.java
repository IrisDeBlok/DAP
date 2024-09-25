package org.example;

import org.example.DAO.AdresDAOPsql;
import org.example.domain.Adres;
import org.example.domain.Reiziger;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        testReiziger();
        closeConnection(c);
    }

    public static void testReiziger() throws SQLException {
        Connection c = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM reiziger";
        pstmt = c.prepareStatement(sqlSelect);
        rs = pstmt.executeQuery();

        System.out.println("Alle reizigers:");

        AdresDAOPsql adresDao = new AdresDAOPsql();

        while (rs.next()) {
            Long id = (long) rs.getInt("reiziger_id");
            String voorletters = rs.getString("voorletters");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            Date geboortedatum = rs.getDate("geboortedatum");

            Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);
            Adres adres = adresDao.findByReiziger(reiziger);

            System.out.println("Reiziger {" + reiziger.toString() + ", Adres {" + (adres != null ? adres.toString() : "Geen adres gevonden") + "}");
        }

        rs.close();
        pstmt.close();
    }

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }
}