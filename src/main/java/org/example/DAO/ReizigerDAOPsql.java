package org.example.DAO;

import org.example.domain.Reiziger;
import org.example.DAO.interfaces.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    Connection c = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException  {
        String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum, adres_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setLong(1, reiziger.getId());
            pstmt.setString(2, reiziger.getVoorletters());
            pstmt.setString(3, reiziger.getTussenvoegsel());
            pstmt.setString(4, reiziger.getAchternaam());
            pstmt.setDate(5, reiziger.getGeboortedatum());
            pstmt.setLong(6, reiziger.getAdres().getId());


            int rowsAffected = pstmt.executeUpdate();
            c.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error saving Reiziger", e);
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        String query = "DELETE FROM reiziger WHERE reiziger_id = ?";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setLong(1, reiziger.getId());
            int deletedRows = pstmt.executeUpdate();
            c.commit();
            return deletedRows > 0;
        } catch (SQLException e) {
            throw new SQLException("Error deleting Reiziger", e);
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException  {
        String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id = ?";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setString(1, reiziger.getVoorletters());
            pstmt.setString(2, reiziger.getTussenvoegsel());
            pstmt.setString(3, reiziger.getAchternaam());
            pstmt.setDate(4, reiziger.getGeboortedatum());
            pstmt.setLong(5, reiziger.getId());

            int updatedRows = pstmt.executeUpdate();
            c.commit();
            return updatedRows > 0;
        } catch (SQLException e) {
            throw new SQLException("Error updating Reiziger", e);
        }
    }

    @Override
    public List<Reiziger> findAll() throws SQLException  {
        List<Reiziger> reizigers = new ArrayList<>();
        Connection c = getConnection();
        String query = "SELECT * FROM reiziger";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Reiziger reiziger = new Reiziger();
            reiziger.setId((long) rs.getInt("reiziger_id"));
            reiziger.setVoorletters(rs.getString("voorletters"));
            reiziger.setTussenvoegsel(rs.getString("tussenvoegsel"));
            reiziger.setAchternaam(rs.getString("achternaam"));
            reiziger.setGeboortedatum(rs.getDate("geboortedatum"));

            reizigers.add(reiziger);
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findByGbDatum(Date date) throws SQLException  {
        List<Reiziger> reizigers = new ArrayList<>();
        Connection c = getConnection();
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
        return reizigers;
    }

    @Override
    public Reiziger findById(long id) throws SQLException  {
        Connection c = getConnection();
        String query = "SELECT * FROM reiziger WHERE reiziger_id = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, id);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            Reiziger reiziger = new Reiziger();
            reiziger.setId((long) rs.getInt("reiziger_id"));
            reiziger.setVoorletters(rs.getString("voorletters"));
            reiziger.setTussenvoegsel(rs.getString("tussenvoegsel"));
            reiziger.setAchternaam(rs.getString("achternaam"));
            reiziger.setGeboortedatum(rs.getDate("geboortedatum"));
            return reiziger;
        }
        return null;
    }
}