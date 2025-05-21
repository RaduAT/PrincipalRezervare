package org.example.rezervaresali.repos;

import org.example.rezervaresali.entities.RezervareSali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface RezervareSaliRepo extends JpaRepository<RezervareSali, Integer> {

    List<RezervareSali> findAllByIdsala(int id);

    @Transactional
    void deleteByidrezervareIn(int[] ids);

}
