package org.example.rezervaresali.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "rezervari")

public class RezervareSali {

    //Variabile Clasa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public long idrezervare;

    @Column(name = "idsala")
    private int idsala;

    @Column(name = "idstudent")
    private int idstudent;

    @Column(name = "idprofesor")
    private int idprofesor;

    @Column(name = "starthour")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date starthour;

    @Column(name = "endhour")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endhour;



}
