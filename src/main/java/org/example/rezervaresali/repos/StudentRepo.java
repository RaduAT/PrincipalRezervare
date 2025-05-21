package org.example.rezervaresali.repos;

import org.example.rezervaresali.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

     List <Student> findByMail(String mail);

     @Transactional
     void deleteByidstudentIn(int[] ids);


}
