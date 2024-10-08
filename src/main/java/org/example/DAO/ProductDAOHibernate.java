package org.example.DAO;

import org.example.domain.OVChipkaart;
import org.example.domain.Product;
import org.example.DAO.interfaces.ProductDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {

    private SessionFactory sessionFactory;

    public ProductDAOHibernate() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public boolean save(Product product) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(product);
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
    public boolean delete(Product product) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(product);
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
    public boolean update(Product product) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(product);
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
    public Product findByOVChipkaart(OVChipkaart ovChipkaart) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p FROM Product p JOIN p.ovChipkaarten o WHERE o.kaart_nummer = :kaartNummer";
            return session.createQuery(hql, Product.class)
                    .setParameter("kaartNummer", ovChipkaart.getKaart_nummer())
                    .uniqueResult();
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }
}
