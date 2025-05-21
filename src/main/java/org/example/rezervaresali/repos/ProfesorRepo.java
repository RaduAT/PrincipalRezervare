package org.example.rezervaresali.repos;

import org.example.rezervaresali.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
public interface ProfesorRepo extends JpaRepository<Profesor, Integer> {

    List <Profesor> findByMail(String mail);

    @Transactional
    void deleteByidprofesorIn(int[] ids);

}
