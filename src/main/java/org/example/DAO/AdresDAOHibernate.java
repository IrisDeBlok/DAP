package org.example.DAO;

import org.example.domain.Adres;
import org.example.domain.Reiziger;
import org.example.domain.interfaces.AdresDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory sessionFactory;
    public AdresDAOHibernate() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public boolean save(Adres adres) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(adres);
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
    public boolean delete(Adres adres) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(adres);
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
    public boolean update(Adres adres) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(adres);
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
    public Adres findByReiziger(Reiziger r) {
        try (Session session = sessionFactory.openSession()) {
            Query<Adres> query = session.createQuery("FROM Adres WHERE id = :reizigerId", Adres.class);
            query.setParameter("reizigerId", r.getId());
            return query.uniqueResult();
        }
    }

    @Override
    public List<Adres> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Adres", Adres.class).list();
        }
    }
}
