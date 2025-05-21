package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.Admin;
import org.example.rezervaresali.repos.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AdminRepo repo;

    //Constructors
    @Autowired
    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    public int getAdminFlag(){

        Admin admin = repo.getById(1);

        return admin.getFlagupdown();
    }

    public void setAdminFlag1(){

        Admin admin = repo.getAdminByFlagupdown(1);
        admin.setFlagupdown(0);

        repo.save(admin);

    }

    public void setAdminFlag0(){

        Admin admin = repo.getAdminByFlagupdown(0);
        admin.setFlagupdown(1);

        repo.save(admin);

    }


}
