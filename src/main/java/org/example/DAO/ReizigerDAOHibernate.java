package org.example.DAO;

import org.example.domain.Reiziger;
import org.example.domain.interfaces.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ReizigerDAOHibernate implements ReizigerDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(reiziger);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> reizigers = new ArrayList<>();
        Transaction t = null;

        try (Session session = sessionFactory.openSession()) {
            t = session.beginTransaction();

            String hql = "FROM Reiziger";
            Query<Reiziger> query = session.createQuery(hql, Reiziger.class);

            reizigers = query.list();

            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }

        return reizigers;
    }
    @Override
    public List<Reiziger> findByGbDatum(Date date) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            Query<Reiziger> query = session.createQuery("FROM Reiziger WHERE geboortedatum = :date", Reiziger.class);
            query.setParameter("date", date);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Reiziger findById(int id) throws SQLException  {
        Transaction t = null;
        Reiziger reiziger = null;

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Reiziger WHERE id = :id";
            Query<Reiziger> query = session.createQuery(hql, Reiziger.class);
            query.setParameter("id", id);

            List<Reiziger> reizigers = query.list();

            t.commit();

            if (!reizigers.isEmpty()) {
                reiziger = reizigers.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reiziger;
    }
}
