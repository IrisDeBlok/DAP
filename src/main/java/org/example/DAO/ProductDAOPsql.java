package org.example.DAO;

import org.example.domain.Adres;
import org.example.domain.OVChipkaart;
import org.example.domain.Product;
import org.example.domain.interfaces.ProductDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(Product product) throws SQLException {
        String query = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setLong(1, product.getProduct_nummer());
            pstmt.setString(2, product.getNaam());
            pstmt.setString(3, product.getBeschrijving());
            pstmt.setDouble(4, product.getPrijs());


            int rowsAffected = pstmt.executeUpdate();
            c.commit();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error saving product", e);
        }
    }

    @Override
    public boolean delete(Product product) throws SQLException{
        String query = "DELETE FROM product WHERE product_nummer = ?";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setLong(1, product.getProduct_nummer());
            int deletedRows = pstmt.executeUpdate();
            c.commit();
            return deletedRows > 0;
        } catch (SQLException e) {
            throw new SQLException("Error deleting Product", e);
        }
    }

    @Override
    public boolean update(Product product) throws SQLException {
        String query = "UPDATE product SET naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?";
        try (Connection c = getConnection(); PreparedStatement pstmt = c.prepareStatement(query)) {
            c.setAutoCommit(false);

            pstmt.setString(1, product.getNaam());
            pstmt.setString(2, product.getBeschrijving());
            pstmt.setDouble(3, product.getPrijs());
            pstmt.setInt(4, product.getProduct_nummer());

            int updatedRows = pstmt.executeUpdate();
            c.commit();
            return updatedRows > 0;
        } catch (SQLException e) {
            throw new SQLException("Error updating Product", e);
        }
    }

    @Override
    public Product findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        Connection c = getConnection();
        Product product = null;

        String query = "SELECT * FROM ov_chipkaart_product WHERE kaart_nummer = ?";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);
        pstmt.setInt(1, ovChipkaart.getKaartNummer());
        rs = pstmt.executeQuery();

        if (rs.next()) {
            product = new Product();
            product.setProduct_nummer(rs.getInt("product_nummer"));

            rs.close();
            pstmt.close();
        }

        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> producten = new ArrayList<>();
        Connection c = getConnection();
        String query = "SELECT * FROM product";
        pstmt = c.prepareStatement(query);
        c.setAutoCommit(false);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProduct_nummer(rs.getInt("product_nummer"));
            product.setNaam(rs.getString("naam"));
            product.setBeschrijving(rs.getString("beschrijving"));
            product.setPrijs(rs.getDouble("prijs"));

            producten.add(product);
        }
        return producten;
    }
}
