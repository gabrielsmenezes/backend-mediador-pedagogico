package com.example.backapi.bullying.services;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.bullying.repositories.BullyingRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BullyingService {
    @Autowired
    BullyingRepository bullyingRepository;

    public Bullying save(Bullying bullying) throws CampoObrigatorio, LimiteDeObjetosAtingido {

        verificarSeJaEstaCadastrado();

        if (bullying.getLinkDoFormulario() == null || bullying.getLinkDoFormulario().isEmpty()){
            throw new CampoObrigatorio("O link é obrigatório");
        }


        return bullyingRepository.save(bullying);
    }

    private void verificarSeJaEstaCadastrado() throws LimiteDeObjetosAtingido {
        if (!bullyingRepository.findAll().isEmpty()){
            throw new LimiteDeObjetosAtingido("Bullying ja cadastrado!");
        }
    }

    public Bullying update(Bullying bullying) throws LimiteDeObjetosAtingido, CampoObrigatorio {

        if (bullying.getLinkDoFormulario() == null || bullying.getLinkDoFormulario().isEmpty()){
            throw new CampoObrigatorio("O link é obrigatório");
        }

        List<Bullying> bullyingsSalvos = bullyingRepository.findAll();

        if (!bullyingsSalvos.isEmpty()){
            bullying.setId(bullyingsSalvos.get(0).getId());
        }

        return bullyingRepository.save(bullying);
    }
}
