package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.Sala;
import org.example.rezervaresali.entities.Student;
import org.example.rezervaresali.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentService {

    private final StudentRepo repo;

    //Constructor
    @Autowired
    public StudentService(StudentRepo repo){
        this.repo = repo;
    }

    //Functie de Gasire TOATE Informatii Studenti
    public List<Student> findAllStudentiService() {
        return repo.findAll();
    }

    //Functii
    public Student addStudentService(String nume, String prenume, String serie, String mail, String telefon, String parola){

        //Facem un obiect de tip Student
        Student student = new Student();

        //Setam Valori
        student.setNume(nume);
        student.setPrenume(prenume);
        student.setSerie(serie);
        student.setMail(mail);
        student.setTelefon(telefon);
        student.setParola(parola);

        //Salvam in baza de date
        return repo.save(student);
    }

    public List<Student> getStudentByMail (String mail){
    List<Student> studenti = repo.findByMail(mail);
        return studenti;
    }

    //Functie de Stergere din Baza de Date Studenti
    public void deleteStudentByIdService(int[] idStudent){
        repo.deleteByidstudentIn(idStudent);
    }


}
