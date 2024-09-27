package org.example.domain;

import javax.persistence.*;

@Entity(name = "Adres")
@Table(name = "adres")
public class Adres {
    @Id
    @GeneratedValue
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
    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public Adres() {}

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

    @Override
    public String toString() {
        return String.format("#%d %s-%s", id, postcode, huisnummer);
    }
}