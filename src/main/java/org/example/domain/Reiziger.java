package org.example.domain;

import java.sql.Date;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    public Reiziger() {}
    public Reiziger(int rid, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = rid;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public void setReizigerId(int rid) {
        this.reiziger_id = rid;
    }

    public int getReizigerId() {
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
        String formattedReiziger;
        if(tussenvoegsel != null) {
            formattedReiziger = voorletters + " " + tussenvoegsel + " " + achternaam + " (" + geboortedatum + ")";
        } else {
            formattedReiziger = voorletters + " " + achternaam + " (" + geboortedatum + ")";
        }

        return formattedReiziger;
    }
}
