package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.sobre_escola.repositories.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EscolaService {

    @Autowired
    EscolaRepository escolaRepository;

    public Escola save(Escola escola){
        return escolaRepository.save(escola);
    }

}
