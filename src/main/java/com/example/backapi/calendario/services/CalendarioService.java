package com.example.backapi.calendario.services;

import com.example.backapi.calendario.domain.Calendario;
import com.example.backapi.calendario.repositories.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarioService {

    @Autowired
    CalendarioRepository calendarioRepository;

    public Calendario save(Calendario calendario){
        return calendarioRepository.save(calendario);
    }


}
