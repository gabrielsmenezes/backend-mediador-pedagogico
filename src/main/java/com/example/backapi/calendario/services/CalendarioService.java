package com.example.backapi.calendario.services;

import com.example.backapi.calendario.domain.Calendario;
import com.example.backapi.calendario.repositories.CalendarioRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {

    @Autowired
    CalendarioRepository calendarioRepository;

    public Calendario save(Calendario calendario) throws CampoObrigatorio, LimiteDeObjetosAtingido {

        if (calendario.getLinkDoCalendario() == null||calendario.getLinkDoCalendario().isEmpty() ){
            throw new CampoObrigatorio("Link do calendario é obrigatório");
        }

        List<Calendario> calendariosSalvos = calendarioRepository.findAll();

        if (!calendariosSalvos.isEmpty()){
            throw new LimiteDeObjetosAtingido("Calendario ja cadastrado anteriormente");
        }

        return calendarioRepository.save(calendario);
    }


}
