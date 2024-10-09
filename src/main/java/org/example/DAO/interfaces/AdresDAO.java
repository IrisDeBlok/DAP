package org.example.DAO.interfaces;

import org.example.domain.Adres;
import org.example.domain.Reiziger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres) throws SQLException;
    boolean update(Adres adres) throws SQLException ;
    boolean delete(Adres adres) throws SQLException ;
    Adres findByReiziger(Reiziger r) throws SQLException ;
    List<Adres> findAll() throws SQLException ;
}