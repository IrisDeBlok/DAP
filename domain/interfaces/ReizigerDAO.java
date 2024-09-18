package org.example.domain.interfaces;

import org.example.domain.Reiziger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger) throws SQLException;
    boolean update(Reiziger reiziger) throws SQLException ;
    boolean delete(Reiziger reiziger) throws SQLException ;
    Reiziger findById(int id) throws SQLException ;
    List<Reiziger> findByGbDatum(Date date) throws SQLException ;
    List<Reiziger> findAll() throws SQLException ;
}
