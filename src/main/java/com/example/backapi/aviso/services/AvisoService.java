package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.repositories.AvisoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;


    public Aviso findById(Integer id){

        Optional<Aviso> entidadeGenerica = avisoRepository.findById(id);

        return entidadeGenerica.orElseThrow(() -> new ObjectNotFoundException(entidadeGenerica.getClass().getName(), "Objeto não encontrado do tipo" + entidadeGenerica.getClass().getName() + " do id " + id) );
    }

    public Aviso save(Aviso aviso) throws CampoObrigatorio, DataIntegrityViolationException {
        if (aviso.getTitulo() == null || (aviso.getDescricao() == null && aviso.getLinks().isEmpty())){
            throw new CampoObrigatorio("Os campos Descricao ou link devem existir");
        }
        Date date=new java.util.Date();
        aviso.setDataDeCriacao(date);
        Aviso resposta = avisoRepository.save(aviso);
        return resposta;

    }

    public Page<Aviso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return avisoRepository.findAll(pageRequest);
    }

    public Aviso update(Aviso aviso) throws CampoObrigatorio {
        findById(aviso.getId());

        return save(aviso);

    }

    public void delete(Integer id) {

        avisoRepository.deleteById(id);

    }

    public List<Aviso> findAll(){
        return avisoRepository.findAll();
    }

}
