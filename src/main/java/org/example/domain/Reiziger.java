package org.example.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Reiziger")
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @GeneratedValue
    @Column(name = "reiziger_id")
    private Long id;
    @Column(name = "voorletters")
    private String voorletters;
    @Column(name = "tussenvoegsel")
    private String tussenvoegsel;
    @Column(name = "achternaam")
    private String achternaam;
    @Column(name = "geboortedatum")
    private Date geboortedatum;

    public Reiziger() {}
    public Reiziger(Long id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setId(Long id) {
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
        String formattedReiziger;
        if(tussenvoegsel != null) {
            formattedReiziger = voorletters + " " + tussenvoegsel + " " + achternaam + " (" + geboortedatum + ")";
        } else {
            formattedReiziger = voorletters + " " + achternaam + " (" + geboortedatum + ")";
        }

        return formattedReiziger;
    }
}
