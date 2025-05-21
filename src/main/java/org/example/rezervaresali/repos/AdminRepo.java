package org.example.rezervaresali.repos;

import org.example.rezervaresali.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
public interface AdminRepo extends JpaRepository<Admin, Integer> {

        public Admin getAdminByFlagupdown(int flag);


}
