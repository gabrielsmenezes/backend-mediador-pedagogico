package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.repositories.AvisoRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Optional;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;


    public Aviso findById(Integer id){

        Optional<Aviso> entidadeGenerica = avisoRepository.findById(id);

        return entidadeGenerica.orElseThrow(() -> new ObjectNotFoundException(entidadeGenerica.getClass().getName(), "Objeto não encontrado do tipo" + entidadeGenerica.getClass().getName() + " do id " + id) );
    }

    public Aviso save(Aviso aviso) throws DataException, ConstraintViolationException {

        Date date=new java.util.Date();
        aviso.setDataDeCriacao(date);
        Aviso resposta = avisoRepository.save(aviso);
        return resposta;

    }

    public Page<Aviso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return avisoRepository.findAll(pageRequest);
    }

    public Aviso update(Aviso aviso){
        findById(aviso.getId());

        return avisoRepository.save(aviso);

    }

    public void delete(Integer id) {

        avisoRepository.deleteById(id);

    }
}
