package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.Profesor;
import org.example.rezervaresali.entities.Student;
import org.example.rezervaresali.repos.ProfesorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepo repo;

    //Constructor
    @Autowired
    public ProfesorService(ProfesorRepo repo) {this.repo = repo;}

    //Functie de Gasire TOATE Informatii Profesori
    public List<Profesor> findAllProfesoriService() {
        return repo.findAll();
    }

    //Functii
    public Profesor addProfesorService(String nume, String prenume, String departament, String mail, String telefon, String parola){

        //Facem un obiect de tip Profesor
        Profesor profesor = new Profesor();

        //Setam Valori
        profesor.setNume(nume);
        profesor.setPrenume(prenume);
        profesor.setDepartament(departament);
        profesor.setMail(mail);
        profesor.setTelefon(telefon);
        profesor.setParola(parola);

        //Salvam in baza de date
        return repo.save(profesor);


    }

    public List<Profesor> getProfesorByMail (String mail){
        List<Profesor> profesori = repo.findByMail(mail);
        return profesori;
    }

    //Functie de Stergere din Baza de Date Profesori
    public void deleteProfesorByIdService(int[] idProfesor){
        repo.deleteByidprofesorIn(idProfesor);
    }









}
