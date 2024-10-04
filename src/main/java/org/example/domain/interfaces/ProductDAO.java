package org.example.domain.interfaces;

import org.example.domain.OVChipkaart;
import org.example.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    boolean save(Product adres) throws SQLException;
    boolean update(Product adres) throws SQLException ;
    boolean delete(Product adres) throws SQLException ;
    Product findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException;
    List<Product> findAll() throws SQLException ;
}