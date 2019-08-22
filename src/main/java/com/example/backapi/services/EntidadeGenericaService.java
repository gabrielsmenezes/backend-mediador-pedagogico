package com.example.backapi.services;

import com.example.backapi.domain.EntidadeGenerica;
import com.example.backapi.repositories.EntidadeGenericaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntidadeGenericaService {

    @Autowired
    private EntidadeGenericaRepository entidadeGenericaRepository;


    public EntidadeGenerica findById(Integer id){

        Optional<EntidadeGenerica> categoria = entidadeGenericaRepository.findById(id);

        return categoria.orElseThrow(() -> new ObjectNotFoundException(categoria.getClass().getName(), "Objeto não encontrado do tipo" + categoria.getClass().getName() + " do id " + id) );
    }
}
