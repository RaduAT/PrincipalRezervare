package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.Sala;
import org.example.rezervaresali.repos.SalaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepo repo;

    @Autowired
    public SalaService(SalaRepo repo) {this.repo = repo;}

    //Functii

    //Functie de Gasire TOATE Informatii Sali
    public List<Sala> findAllSaliService() {
        return repo.findAll();
    }


    //Service de Adaugare Sala Noua
    public Sala addSalaService(String nume, String etaj, Integer locuri){

        //Facem un obiect de tip Sala
        Sala sala = new Sala();

        //Setam Valori
        sala.setNume(nume);
        sala.setEtaj(etaj);
        sala.setLocuri(locuri);

        //Salvam in baza de date
        return repo.save(sala);

    }

    //Functie de Stergere din Baza de Date Sali
    public void deleteSalaByIdService(int[] idSala){
        repo.deleteByidsalaIn(idSala);
    }



}
