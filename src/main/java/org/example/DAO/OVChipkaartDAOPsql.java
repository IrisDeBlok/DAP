package org.example.DAO;

import org.example.domain.Adres;
import org.example.domain.OVChipkaart;
import org.example.domain.Reiziger;
import org.example.domain.interfaces.OVChipkaartDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) throws SQLException {
        Connection c = getConnection();
        String query = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo) VALUES (?, ?, ?, ?)";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, ovChipkaart.getKaartNummer());
        pstmt.setDate(2, ovChipkaart.getGeldigTot());
        pstmt.setInt(3, ovChipkaart.getKlasse());
        pstmt.setInt(4, ovChipkaart.getSaldo());

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) throws SQLException {
        Connection c = getConnection();
        String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, ovChipkaart.getKaartNummer());
        int deletedRows = pstmt.executeUpdate();
        c.commit();

        return deletedRows > 0;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) throws SQLException {
        Connection c = getConnection();
        String query = "UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ? WHERE kaart_nummer = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setDate(1, ovChipkaart.getGeldigTot());
        pstmt.setInt(2, ovChipkaart.getKlasse());
        pstmt.setInt(3, ovChipkaart.getSaldo());
        pstmt.setLong(4, ovChipkaart.getKaartNummer());

        int updatedRows = pstmt.executeUpdate();
        return updatedRows > 0;
    }

    @Override
    public OVChipkaart findByReiziger(Reiziger r) throws SQLException  {
        Connection c = getConnection();
        OVChipkaart ovchipkaart = null;
        String query = "SELECT * FROM ov_chipkaart WHERE reiziger_id = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        pstmt.setLong(1, r.getId());

        rs = pstmt.executeQuery();

        if (rs.next()) {
            ovchipkaart = new OVChipkaart();
            ovchipkaart.setKaartNummer(rs.getInt("kaart_nummer"));
            ovchipkaart.setGeldigTot(rs.getDate("geldig_tot"));
            ovchipkaart.setKlasse(rs.getInt("klasse"));
            ovchipkaart.setSaldo(rs.getInt("saldo"));

            rs.close();
            pstmt.close();
        }

        return ovchipkaart;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException  {
        List<OVChipkaart> ovchipkaarten = new ArrayList<>();
        Connection c = getConnection();
        String query = "SELECT * FROM ov_chipkaart";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);

        while (rs.next()) {
            OVChipkaart ovchipkaart = new OVChipkaart();
            ovchipkaart.setKaartNummer(rs.getInt("kaart_nummer"));
            ovchipkaart.setGeldigTot(rs.getDate("geldig_tot"));
            ovchipkaart.setKlasse(rs.getInt("klasse"));
            ovchipkaart.setSaldo(rs.getInt("saldo"));

            ovchipkaarten.add(ovchipkaart);
        }
        return ovchipkaarten;
    }
}
