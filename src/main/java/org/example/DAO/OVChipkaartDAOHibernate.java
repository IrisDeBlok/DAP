package org.example.DAO;

import org.example.domain.Adres;
import org.example.domain.OVChipkaart;
import org.example.domain.Reiziger;
import org.example.domain.interfaces.OVChipkaartDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {

    private SessionFactory sessionFactory;

    public OVChipkaartDAOHibernate() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(ovChipkaart);
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
    public boolean delete(OVChipkaart ovChipkaart) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(ovChipkaart);
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
    public boolean update(OVChipkaart ovChipkaart) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(ovChipkaart);
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
    public OVChipkaart findByReiziger(Reiziger r) {
        try (Session session = sessionFactory.openSession()) {
            Query<OVChipkaart> query = session.createQuery("FROM OVChipkaart WHERE id = :reizigerId", OVChipkaart.class);
            query.setParameter("reizigerId", r.getId());
            return query.uniqueResult();
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM OVChipkaart", OVChipkaart.class).list();
        }
    }
}
