package org.example.rezervaresali.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sala")

public class Sala {

    //Variabile Clasa

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public long idsala;

    @Column(name = "nume")
    private String nume;

    @Column(name = "etaj")
    private String etaj;

    @Column(name = "locuri")
    private Integer locuri;

    //Getters & Setters


    public long getIdsala() {
        return idsala;
    }

    public void setIdsala(long idsala) {
        this.idsala = idsala;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEtaj() {
        return etaj;
    }

    public void setEtaj(String etaj) {
        this.etaj = etaj;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }
}
