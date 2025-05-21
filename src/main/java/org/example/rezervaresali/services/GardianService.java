package org.example.rezervaresali.services;

import org.example.rezervaresali.entities.Gardian;
import org.example.rezervaresali.repos.GardianRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class GardianService {

    private final GardianRepo repo;

    //Constructors
    @Autowired
    public GardianService(GardianRepo repo) {
        this.repo = repo;
    }
}
