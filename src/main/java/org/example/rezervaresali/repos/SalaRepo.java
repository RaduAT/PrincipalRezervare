package org.example.rezervaresali.repos;

import org.example.rezervaresali.entities.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface SalaRepo extends JpaRepository<Sala, Integer> {

    @Transactional
    void deleteByidsalaIn(int[] ids);

}
