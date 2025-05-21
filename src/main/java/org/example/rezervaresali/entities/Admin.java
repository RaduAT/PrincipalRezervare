package org.example.rezervaresali.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")

public class Admin {

    //Variabile Clasa

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idadmin;

    @Column(name = "mail")
    private String mail;

    @Column(name = "parola")
    private String parola;

    @Column(name = "flagupdown")
    private Integer flagupdown;

    //Getters & Setters

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Integer getFlagupdown() {
        return flagupdown;
    }

    public void setFlagupdown(Integer flagupdown) {
        this.flagupdown = flagupdown;
    }


}
