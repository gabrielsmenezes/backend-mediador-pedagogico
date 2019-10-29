package com.example.backapi.biblioteca.services;

import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.repositories.TopicoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public Topico save(Topico topico) throws CampoObrigatorio {

        if(topico.getNome() == null || topico.getNome().isEmpty()){
            throw new CampoObrigatorio("Nome é obrigatório");
        }

        return topicoRepository.save(topico);
    }
}
