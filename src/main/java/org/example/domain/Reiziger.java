package org.example.domain;

import org.example.DAO.AdresDAOPsql;
import org.example.DAO.OVChipkaartDAOHibernate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

@Entity(name = "Reiziger")
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name = "reiziger_id")
    private Long reiziger_id;
    @Column(name = "voorletters")
    private String voorletters;
    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;
    @Column(name = "achternaam")
    private String achternaam;
    @Column(name = "geboortedatum")
    private Date geboortedatum;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adres_id")
    private Adres adres;
    @OneToMany(mappedBy="reiziger")
    private Set<OVChipkaart> ov_chipkaart;

    public Reiziger() {}
    public Reiziger(Long id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setId(Long id) {
        this.reiziger_id = id;
    }

    public Long getId() {
        return reiziger_id;
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
                reiziger_id,
                voorletters,
                (tussenvoegsel != null && !tussenvoegsel.isEmpty() ? " " + tussenvoegsel : ""),
                achternaam,
                geboortedatum.toString());
    }
}