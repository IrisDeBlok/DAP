package org.example.domain.interfaces;

import org.example.domain.OVChipkaart;
import org.example.domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {
    boolean save(OVChipkaart ovChipkaart) throws SQLException;
    boolean update(OVChipkaart ovChipkaart) throws SQLException ;
    boolean delete(OVChipkaart ovChipkaart) throws SQLException ;
    OVChipkaart findByReiziger(Reiziger r) throws SQLException ;
    List<OVChipkaart> findAll() throws SQLException ;
}