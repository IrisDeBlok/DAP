package org.example.domain.interfaces;

import org.example.domain.Reiziger;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    public boolean save(Reiziger reiziger);
    public boolean update(Reiziger reiziger);
    public boolean delete(Reiziger reiziger);
    public Reiziger findById(int id);
    public List<Reiziger> findByGbDatum(Date date);
    public List<Reiziger> findAll();
}
