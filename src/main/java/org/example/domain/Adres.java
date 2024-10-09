package org.example.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Adres")
@Table(name = "adres")
public class Adres {
    @Id
    @Column(name = "adres_id")
    private Long id;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "huisnummer")
    private String huisnummer;
    @Column(name = "straat")
    private String straat;
    @Column(name = "woonplaats")
    private String woonplaats;
    @OneToOne(mappedBy = "adres")
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public Adres() {}
    public Adres(Long id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getStraat() {return straat;}

    public void setWoonplaats(String woonplaats) {this.woonplaats = woonplaats;}

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    @Override
    public String toString() {
        return String.format("#%d %s-%s", id, postcode, huisnummer);
    }
}