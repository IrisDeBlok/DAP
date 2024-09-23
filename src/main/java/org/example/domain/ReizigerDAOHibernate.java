package org.example.domain;

import org.example.domain.interfaces.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ReizigerDAOHibernate implements ReizigerDAO {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Transaction t;
        Session session = null;
        boolean success = false;

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "VALUES (:id, :voorletters, :tussenvoegsel, :achternaam, :geboortedatum)";
            SQLQuery query = session.createSQLQuery(sqlQuery);

            query.setParameter("id", reiziger.getId());
            query.setParameter("voorletters", reiziger.getVoorletters());
            query.setParameter("tussenvoegsel", reiziger.getTussenvoegsel());
            query.setParameter("achternaam", reiziger.getAchternaam());
            query.setParameter("geboortedatum", reiziger.getGeboortedatum());

            query.executeUpdate();
            t.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return success;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Transaction t;
        Session session = null;
        boolean success = false;

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "DELETE FROM reiziger WHERE reiziger_id = :id";
            SQLQuery query = session.createSQLQuery(sqlQuery);

            query.setParameter("id", reiziger.getId());

            query.executeUpdate();
            t.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return success;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Transaction t;
        Session session = null;
        boolean success = false;

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "UPDATE reiziger SET voorletters = :voorletters, tussenvoegsel = :tussenvoegsel, achternaam = :achternaam, geboortedatum = :geboortedatum WHERE reiziger_id = :id";
            SQLQuery query = session.createSQLQuery(sqlQuery);

            query.setParameter("voorletters", reiziger.getVoorletters());
            query.setParameter("tussenvoegsel", reiziger.getTussenvoegsel());
            query.setParameter("achternaam", reiziger.getAchternaam());
            query.setParameter("geboortedatum", reiziger.getGeboortedatum());
            query.setParameter("id", reiziger.getId());


            query.executeUpdate();
            t.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return success;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException  {
        Transaction t;
        Session session = null;
        List<Reiziger> reizigers = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "SELECT * FROM reiziger";
            SQLQuery query = session.createSQLQuery(sqlQuery);

            query.executeUpdate();
            t.commit();

            query.addEntity(Reiziger.class);

            reizigers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findByGbDatum(Date date) throws SQLException  {
        Transaction t;
        Session session = null;
        List<Reiziger> reizigers = new ArrayList<>();

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "SELECT * FROM reiziger WHERE geboortedatum = :date";
            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Reiziger.class);

            query.setParameter("date", date);
            query.executeUpdate();
            t.commit();

            reizigers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return reizigers;
    }

    @Override
    public Reiziger findById(int id) throws SQLException  {
        Transaction t;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            t = session.beginTransaction();

            String sqlQuery = "SELECT * FROM reiziger WHERE reiziger_id = ?";
            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Reiziger.class);
            query.setParameter("id", id);

            query.executeUpdate();
            t.commit();

            List<Reiziger> reizigers = query.list();
            if (!reizigers.isEmpty()) {
                return reizigers.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
