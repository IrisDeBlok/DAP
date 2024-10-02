package org.example.domain;

public class Adres {
    private Long id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    public Adres() {}
    public Adres(Long id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
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

    @Override
    public String toString() {
        return String.format("#%d %s-%s", id, postcode, huisnummer);
    }
}
