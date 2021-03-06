package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.sobre_escola.repositories.EscolaRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
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

    public Escola update(Escola escola) throws CampoObrigatorio {

        verificaExistenciaDeCampos(escola.getNome());
        verificaExistenciaDeCampos(escola.getDescricao());
        List<Escola> escolasSalvas = escolaRepository.findAll();

        if (!escolasSalvas.isEmpty()){
            escola.setId(escolasSalvas.get(0).getId());
        }

        return escolaRepository.save(escola);

    }

    public Escola find() throws ObjetoNaoEncontrado {
        List<Escola> escolas = escolaRepository.findAll();
        if (escolas.isEmpty()) {
            throw new ObjetoNaoEncontrado("Escola ainda nao cadastrada");
        }
        return escolas.get(0);
    }
}
