package org.example.domain;

import java.sql.Date;

public class OVChipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private Reiziger reiziger;


    public OVChipkaart() {}
    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, int saldo) {
        this.kaart_nummer = kaartNummer;
        this.geldig_tot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaart_nummer = kaartNummer;
    }

    public int getKaartNummer() {
        return kaart_nummer;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldig_tot = geldigTot;
    }

    public Date getGeldigTot() {
        return geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {return saldo;}

    @Override
    public String toString() {
        return String.format("#%d %tF %d %d", kaart_nummer, geldig_tot, klasse, saldo);
    }
}
