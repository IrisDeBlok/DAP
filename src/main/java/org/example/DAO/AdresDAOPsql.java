package org.example.DAO;

import org.example.domain.Adres;
import org.example.domain.Reiziger;
import org.example.DAO.interfaces.AdresDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(Adres adres) throws SQLException  {
        Connection c = getConnection();
        String query = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
        pstmt = c.prepareStatement(query);
        try {
            c.setAutoCommit(false);

            pstmt.setLong(1, adres.getId());
            pstmt.setString(2, adres.getPostcode());
            pstmt.setString(3, adres.getHuisnummer());
            pstmt.setString(4, adres.getStraat());
            pstmt.setString(5, adres.getWoonplaats());
            pstmt.setLong(6, adres.getReiziger().getId());

            int rowsAffected = pstmt.executeUpdate();
            c.commit();

            return rowsAffected > 0;
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        Connection c = getConnection();
        String query = "DELETE FROM adres WHERE adres_id = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, adres.getId());
        int deletedRows = pstmt.executeUpdate();
        c.commit();

        return deletedRows > 0;
    }

    @Override
    public boolean update(Adres adres) throws SQLException  {
        Connection c = getConnection();
        String query = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE adres_id = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setString(1, adres.getPostcode());
        pstmt.setString(2, adres.getHuisnummer());
        pstmt.setString(3, adres.getStraat());
        pstmt.setString(4, adres.getWoonplaats());
        pstmt.setLong(5, adres.getId());

        int updatedRows = pstmt.executeUpdate();
        return updatedRows > 0;
    }

    @Override
    public Adres findByReiziger(Reiziger r) throws SQLException  {
        Connection c = getConnection();
        Adres adres = null;
        String query = "SELECT * FROM adres WHERE reiziger_id = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, r.getId());

        rs = pstmt.executeQuery();

        if (rs.next()) {
            adres = new Adres();
            adres.setId(rs.getLong("adres_id"));
            adres.setPostcode(rs.getString("postcode"));
            adres.setHuisnummer(rs.getString("huisnummer"));
            adres.setStraat(rs.getString("straat"));
            adres.setWoonplaats(rs.getString("woonplaats"));

            rs.close();
            pstmt.close();
        }

        return adres;
    }

    @Override
    public List<Adres> findAll() throws SQLException  {
        List<Adres> adressen = new ArrayList<>();
        Connection c = getConnection();
        String query = "SELECT * FROM adres";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Adres adres = new Adres();
            adres.setId(rs.getLong("adres_id"));
            adres.setPostcode(rs.getString("postcode"));
            adres.setHuisnummer(rs.getString("huisnummer"));
            adres.setStraat(rs.getString("straat"));
            adres.setWoonplaats(rs.getString("woonplaats"));

            adressen.add(adres);
        }
        rs.close();
        pstmt.close();
        c.close();

        return adressen;
    }

}