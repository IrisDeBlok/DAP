package org.example;

import org.example.database.DatabaseConnection;
import org.example.domain.Reiziger;
import org.example.domain.ReizigerDAOsql;
import org.example.domain.interfaces.ReizigerDAO;

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

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }
}