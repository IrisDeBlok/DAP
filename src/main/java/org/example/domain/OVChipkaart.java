package org.example.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaart_nummer;
    @Column(name = "geldig_tot")
    private Date geldig_tot;
    @Column(name = "klasse")
    private int klasse;
    @Column(name = "saldo")
    private int saldo;
    @ManyToOne
    @JoinColumn(name="reiziger_id")
    private Reiziger reiziger;
    @ManyToMany
    @JoinTable(
            name = "ovchip_product",
            joinColumns = @JoinColumn(name = "kaart_nummer"),
            inverseJoinColumns = @JoinColumn(name = "product_nummer")
    )
    private Set<Product> producten;

    public OVChipkaart() {}
    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, int saldo) {
        this.kaart_nummer = kaartNummer;
        this.geldig_tot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public Date getGeldig_tot() {
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
