package com.example.backapi.gremio.services;

import com.example.backapi.gremio.domain.Gremio;
import com.example.backapi.gremio.repositories.GremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GremioService {

    @Autowired
    GremioRepository gremioRepository;

    public Gremio save(Gremio gremio){
        return gremioRepository.save(gremio);
    }
}
