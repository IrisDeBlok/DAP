package org.example.domain;

import org.example.database.DatabaseConnection;
import org.example.domain.Reiziger;
import org.example.domain.interfaces.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ReizigerDAOsql implements ReizigerDAO {

    @Override
    public boolean save(Reiziger reiziger) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DatabaseConnection.getConnection();
            String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            pstmt.setInt(1, reiziger.getReizigerId());
            pstmt.setString(2, reiziger.getVoorletters());
            pstmt.setString(3, reiziger.getTussenvoegsel());
            pstmt.setString(4, reiziger.getAchternaam());
            pstmt.setDate(5, reiziger.getGeboortedatum());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DatabaseConnection.getConnection();
            String query = "DELETE FROM reiziger WHERE reiziger_id = ?";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            pstmt.setInt(1, reiziger.getReizigerId());
            int deletedRows = pstmt.executeUpdate();
            c.commit();

            return deletedRows > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            c = DatabaseConnection.getConnection();
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            pstmt.setString(1, reiziger.getVoorletters());
            pstmt.setString(2, reiziger.getTussenvoegsel());
            pstmt.setString(3, reiziger.getAchternaam());
            pstmt.setDate(4, reiziger.getGeboortedatum());
            pstmt.setInt(5, reiziger.getReizigerId());

            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Reiziger> findAll() {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            c = DatabaseConnection.getConnection();
            String query = "SELECT * FROM reiziger";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            while (rs.next()) {
                Reiziger reiziger = new Reiziger();
                reiziger.setReizigerId(rs.getInt("reiziger_id"));
                reiziger.setVoorletters(rs.getString("voorletters"));
                reiziger.setTussenvoegsel(rs.getString("tussenvoegsel"));
                reiziger.setAchternaam(rs.getString("achternaam"));
                reiziger.setGeboortedatum(rs.getDate("geboortedatum"));

                reizigers.add(reiziger);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findByGbDatum(Date date) {
        List<Reiziger> reizigers = new ArrayList<>();
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DatabaseConnection.getConnection();
            String query = "SELECT * FROM reiziger WHERE geboortedatum = ?";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            pstmt.setDate(1, date);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Reiziger reiziger = new Reiziger();
                reiziger.setVoorletters(rs.getString("voorletters"));
                reiziger.setTussenvoegsel(rs.getString("tussenvoegsel"));
                reiziger.setAchternaam(rs.getString("achternaam"));
                reiziger.setGeboortedatum(rs.getDate("geboortedatum"));

                reizigers.add(reiziger);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reizigers;
    }

    @Override
    public Reiziger findById(int id) {
        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            c = DatabaseConnection.getConnection();
            String query = "SELECT * FROM reiziger WHERE reiziger_id = ?";
            pstmt = c.prepareStatement(query);
            c.setAutoCommit(false);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Reiziger reiziger = new Reiziger();
                reiziger.setReizigerId(rs.getInt("reiziger_id"));
                reiziger.setVoorletters(rs.getString("voorletters"));
                reiziger.setTussenvoegsel(rs.getString("tussenvoegsel"));
                reiziger.setAchternaam(rs.getString("achternaam"));
                reiziger.setGeboortedatum(rs.getDate("geboortedatum"));
                return reiziger;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
