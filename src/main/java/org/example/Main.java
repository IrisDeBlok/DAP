package org.example;

import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String value = null;
        try {
            c = DatabaseConnection.getConnection();
            c.setAutoCommit(false);

            String sqlSelect = "SELECT * FROM reiziger";
            pstmt = c.prepareStatement(sqlSelect);
            rs = pstmt.executeQuery();
            
            List<String> reizigers = new ArrayList<>();
            int count = 1;

            System.out.println("Alle reizigers:");

            while (rs.next()) {
                int id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                String geboortedatum = rs.getString("geboortedatum");

                String formattedReiziger;
                if(tussenvoegsel != null) {
                    formattedReiziger = "#" + id + ": " + voorletters + " " + tussenvoegsel + " " + achternaam + " (" + geboortedatum + ")";
                } else {
                    formattedReiziger = "#" + id + ": " + voorletters + " " + achternaam + " (" + geboortedatum + ")";
                }
                reizigers.add(formattedReiziger);

                count++;
            }

            for (String reiziger : reizigers) {
                System.out.println(reiziger);
            }

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}