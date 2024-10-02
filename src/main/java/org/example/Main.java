package org.example;

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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection c = getConnection();
        testReiziger();
        testAdres();
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

        while (rs.next()) {
            Long id = (long) rs.getInt("reiziger_id");
            String voorletters = rs.getString("voorletters");
            String tussenvoegsel = rs.getString("tussenvoegsel");
            String achternaam = rs.getString("achternaam");
            Date geboortedatum = rs.getDate("geboortedatum");

            Reiziger reiziger = new Reiziger(id, voorletters, tussenvoegsel, achternaam, geboortedatum);

            System.out.println(reiziger.toString());
        }


        rs.close();
        pstmt.close();
    }

    public static void testAdres() throws SQLException {
        Connection c = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM adres";
        pstmt = c.prepareStatement(sqlSelect);
        rs = pstmt.executeQuery();

        System.out.println("Alle adressen:");

        while (rs.next()) {
            Long id = (long) rs.getInt("adres_id");
            String postcode = rs.getString("postcode");
            String huisnummer = rs.getString("huisnummer");
            String straat = rs.getString("straat");
            String woonplaats = rs.getString("woonplaats");

            Adres adres = new Adres(id, postcode, huisnummer, straat, woonplaats);

            System.out.println(adres.toString());
        }


        rs.close();
        pstmt.close();
    }

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }
}