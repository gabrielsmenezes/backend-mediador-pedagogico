package com.example.backapi.entidade_generica.services;

import com.example.backapi.entidade_generica.domain.EntidadeGenerica;
import com.example.backapi.entidade_generica.repositories.EntidadeGenericaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntidadeGenericaService {

    @Autowired
    private EntidadeGenericaRepository entidadeGenericaRepository;


    public EntidadeGenerica findById(Integer id){

        Optional<EntidadeGenerica> entidadeGenerica = entidadeGenericaRepository.findById(id);

        return entidadeGenerica.orElseThrow(() -> new ObjectNotFoundException(entidadeGenerica.getClass().getName(), "Objeto não encontrado do tipo" + entidadeGenerica.getClass().getName() + " do id " + id) );
    }

    public EntidadeGenerica save(EntidadeGenerica entidadeGenerica) {

        EntidadeGenerica resposta = entidadeGenericaRepository.save(entidadeGenerica);

        return resposta;

    }

}
