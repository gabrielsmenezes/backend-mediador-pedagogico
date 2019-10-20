package com.example.backapi.bullying.services;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.bullying.repositories.BullyingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BullyingService {
    @Autowired
    BullyingRepository bullyingRepository;

    public Bullying save(Bullying bullying){
        return bullyingRepository.save(bullying);
    }
}
