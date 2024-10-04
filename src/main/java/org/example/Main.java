package org.example;

import org.example.DAO.*;
import org.example.domain.Adres;
import org.example.domain.OVChipkaart;
import org.example.domain.Product;
import org.example.domain.Reiziger;
import org.example.domain.interfaces.AdresDAO;
import org.example.domain.interfaces.OVChipkaartDAO;
import org.example.domain.interfaces.ProductDAO;
import org.example.domain.interfaces.ReizigerDAO;

import java.sql.*;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demoDAP";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Koeskoes123123!";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        testReiziger();
        testAdres();
        testOV_chipkaart();
        testProduct();
        closeConnection(c);
    }

    private static void testReiziger() throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOHibernate();
        System.out.println("\n---------- Test ReizigerDAO -------------");

        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        String gbdatum = "1981-03-14";
        Reiziger save = new Reiziger(77L, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(save);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        Reiziger update = new Reiziger(77L, "A", "", "Donk", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.update() ");
        rdao.update(update);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        Reiziger delete = new Reiziger(77L, null, null, null, java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete() ");
        rdao.delete(delete);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

    }

    public static void testAdres() throws SQLException {
        AdresDAO adao = new AdresDAOHibernate();
        System.out.println("\n---------- Test AdresDAO -------------");

        List<Adres> adres = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adres:");
        for (Adres a : adres) {
            System.out.println(a);
        }
        System.out.println();

        Adres save = new Adres(5L, "4000XM", "58A", "Oudenoord", "Utrecht");
        System.out.print("[Test] Eerst " + adres.size() + " adres, na AdresDAO.save() ");
        adao.save(save);
        adres = adao.findAll();
        System.out.println(adres.size() + " adres\n");

        Adres update = new Adres(5L, "4000XM", "58A", "Nijenoord", "Utrecht");
        System.out.print("[Test] Eerst " + adres.size() + " adres, na AdresDAO.update() ");
        adao.update(update);
        adres = adao.findAll();
        System.out.println(adres.size() + " adres\n");

        Adres delete = new Adres(5L, null, null, null, null);
        System.out.print("[Test] Eerst " + adres.size() + " adres, na AdresDAO.delete() ");
        adao.delete(delete);
        adres = adao.findAll();
        System.out.println(adres.size() + " adres\n");
    }

    public static void testOV_chipkaart() throws SQLException {
        OVChipkaartDAO ovdao = new OVChipkaartDAOHibernate();
        System.out.println("\n---------- Test OVChipkaartDAO -------------");

        List<OVChipkaart> ovchipkaart = ovdao.findAll();
        System.out.println("[Test] OVChipkaartDAO.findAll() geeft de volgende ovchipkaart:");
        for (OVChipkaart o : ovchipkaart) {
            System.out.println(o);
        }
        System.out.println();

        OVChipkaart save = new OVChipkaart(654321, java.sql.Date.valueOf("2027-07-01"), 2, 9);
        System.out.print("[Test] Eerst " + ovchipkaart.size() + " ovchipkaart, na OVChipkaartDAO.save() ");
        ovdao.save(save);
        ovchipkaart = ovdao.findAll();
        System.out.println(ovchipkaart.size() + " ovchipkaart\n");

        OVChipkaart update = new OVChipkaart(654321, java.sql.Date.valueOf("2027-07-01"), 1, 21);
        System.out.print("[Test] Eerst " + ovchipkaart.size() + " ovchipkaart, na OVChipkaartDAO.update() ");
        ovdao.update(update);
        ovchipkaart = ovdao.findAll();
        System.out.println(ovchipkaart.size() + " ovchipkaart\n");

        OVChipkaart delete = new OVChipkaart(654321, java.sql.Date.valueOf("2027-07-01"), 1, 21);
        System.out.print("[Test] Eerst " + ovchipkaart.size() + " ovchipkaart, na OVChipkaartDAO.delete() ");
        ovdao.delete(delete);
        ovchipkaart = ovdao.findAll();
        System.out.println(ovchipkaart.size() + " ovchipkaart\n");
    }

    public static void testProduct() throws SQLException {
        ProductDAO pdao = new ProductDAOHibernate();
        System.out.println("\n---------- Test ProductDAO -------------");

        List<Product> product = pdao.findAll();
        System.out.println("[Test] ProductDAO.findAll() geeft de volgende product:");
        for (Product p : product) {
            System.out.println(p);
        }
        System.out.println();

        Product save = new Product(123456, "pen", "Met een pen kan je op papier schrijven", 1.50);
        System.out.print("[Test] Eerst " + product.size() + " product, na ProductDAO.save() ");
        pdao.save(save);
        product = pdao.findAll();
        System.out.println(product.size() + " product\n");

        Product update = new Product(123456, "potlood", "Met een potlood kan je op papier schrijven", 0.50);
        System.out.print("[Test] Eerst " + product.size() + " product, na ProductDAO.update() ");
        pdao.update(update);
        product = pdao.findAll();
        System.out.println(product.size() + " product\n");

        Product delete = new Product(123456, "potlood", "Met een potlood kan je op papier schrijven", 0.50);
        System.out.print("[Test] Eerst " + product.size() + " product, na ProductDAO.delete() ");
        pdao.delete(delete);
        product = pdao.findAll();
        System.out.println(product.size() + " product\n");

        System.out.println("[Test] ProductDAO.findByOVChipkaart() geeft de volgende product:");
        List<OVChipkaart> productOVChipkaart = pdao.findByOVChipkaart(1);

        for (OVChipkaart p : productOVChipkaart) {
            System.out.println(p);
        }
    }


    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }
}