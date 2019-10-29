package com.example.backapi.biblioteca.services;

import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.repositories.TopicoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;

    public Topico save(Topico topico) throws CampoObrigatorio {

        validarCampo(topico.getNome());

        return topicoRepository.save(topico);
    }

    private void validarCampo(String nome) throws CampoObrigatorio {
        if(nome == null || nome.isEmpty()){
            throw new CampoObrigatorio("Nome é obrigatório");
        }
    }

    public Topico update(Topico topico) throws ObjetoNaoEncontrado, CampoObrigatorio {
        validarCampo(topico.getNome());
        topicoRepository.findById(topico.getId()).orElseThrow(ObjetoNaoEncontrado::new);
        return topicoRepository.save(topico);
    }
}
