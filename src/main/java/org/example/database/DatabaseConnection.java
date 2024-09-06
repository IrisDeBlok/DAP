package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // database url, user, ww
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // maven directory
        Class.forName("org.postgresql.Driver");
        // maakt de connenctie met de db
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}