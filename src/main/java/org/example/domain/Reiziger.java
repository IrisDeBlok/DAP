package org.example.domain;

import org.example.DAO.AdresDAOPsql;

import java.sql.Date;
import java.sql.SQLException;

public class Reiziger {
    private long id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;

    public Reiziger() {}
    public Reiziger(long id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getAchternaam() {
        return achternaam;
    }

    @Override
    public String toString() {
        AdresDAOPsql adresDao = new AdresDAOPsql();
        Adres adres = null;
        try {
            adres = adresDao.findByReiziger(this);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return String.format("#%d %s%s %s, geb. %s",
                id,
                voorletters,
                (tussenvoegsel != null && !tussenvoegsel.isEmpty() ? " " + tussenvoegsel : ""),
                achternaam,
                geboortedatum.toString());
    }
}