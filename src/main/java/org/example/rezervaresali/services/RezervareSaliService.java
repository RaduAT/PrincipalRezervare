package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.RezervareSali;
import org.example.rezervaresali.repos.RezervareSaliRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RezervareSaliService{

    private final RezervareSaliRepo repo;

    @Autowired
    public RezervareSaliService(RezervareSaliRepo repo) {this.repo = repo;}

    //Functie de Gasire TOATE Informatii Rezervari
    public List<RezervareSali> findAllRezervariSaliService() {return repo.findAll();}


    //Functii
    public RezervareSali addRezervareSali(Integer idsala, Integer idstudent, Integer idprofesor, Date starthour, Date endhour){

        //Facem un obiect de tip RezervariSali
        RezervareSali rezervareSali = new RezervareSali();

        //Setam Valori
        rezervareSali.setIdsala(idsala);
        rezervareSali.setIdstudent(idstudent);
        rezervareSali.setIdprofesor(idprofesor);
        rezervareSali.setStarthour(starthour);
        rezervareSali.setEndhour(endhour);

        //Salvam in baza de date
        return repo.save(rezervareSali);

    }

    public List<RezervareSali> findRezervareBySali(int idsala){

        return repo.findAllByIdsala(idsala);

    }

    //Functie de Stergere din Baza de Date Rezervari
    public void deleteRezervareByIdService(int[] idRezervare){
        repo.deleteByidrezervareIn(idRezervare);
    }


}
