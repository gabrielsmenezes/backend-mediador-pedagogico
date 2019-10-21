package com.example.backapi.gremio.services;

import com.example.backapi.gremio.domain.Gremio;
import com.example.backapi.gremio.repositories.GremioRepository;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GremioService {

    @Autowired
    GremioRepository gremioRepository;

    public Gremio save(Gremio gremio) throws LimiteDeObjetosAtingido {

        List<Gremio> lista = gremioRepository.findAll();

        if (!lista.isEmpty()){
            throw new LimiteDeObjetosAtingido("Gremio ja cadastrado anteriormente");
        }

        return gremioRepository.save(gremio);
    }

    public Gremio update(Gremio gremio) {

        List<Gremio> gremiosSalvos = gremioRepository.findAll();

        if (!gremiosSalvos.isEmpty()){
            gremio.setId(gremiosSalvos.get(0).getId());
        }

        return gremioRepository.save(gremio);

        }
}
