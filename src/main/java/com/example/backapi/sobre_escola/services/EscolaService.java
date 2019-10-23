package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.sobre_escola.repositories.EscolaRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscolaService {

    @Autowired
    EscolaRepository escolaRepository;

    public Escola save(Escola escola) throws CampoObrigatorio, LimiteDeObjetosAtingido {
        verificaExistenciaDeCampos(escola.getNome());
        verificaExistenciaDeCampos(escola.getDescricao());

        List<Escola> escolas = escolaRepository.findAll();

        if (!escolas.isEmpty()){
            throw new LimiteDeObjetosAtingido("Já existe uma escola cadastrada anteriormente");
        }

        return escolaRepository.save(escola);
    }

    private void verificaExistenciaDeCampos(String campo) throws CampoObrigatorio {
        if (campo == null || campo.isEmpty()){
            throw new CampoObrigatorio("Campo obrigatório faltante");
        }
    }

}
