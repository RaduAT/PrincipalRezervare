package org.example.rezervaresali.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "student")

public class Student {

    //Variabile Clasa

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idstudent;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "serie")
    private String serie;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "parola")
    private String parola;

    @Column(name = "sesiune")
    private String sesiune;

    //Getters & Setters

    public long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(long idstudent) {
        this.idstudent = idstudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getParola() { return parola; }

    public void setParola(String parola) { this.parola = parola; }

    public String getSesiune() {
        return sesiune;
    }

    public void setSesiune(String sesiune) {
        this.sesiune = sesiune;
    }


}
