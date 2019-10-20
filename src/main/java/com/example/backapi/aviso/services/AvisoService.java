package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import com.example.backapi.aviso.repositories.AvisoRepository;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Aviso save(Aviso aviso) throws CampoObrigatorio, TamanhoDeCampoExcedente {
        if (aviso.getTitulo() == null || (aviso.getDescricao() == null && aviso.getLinks().isEmpty())){
            throw new CampoObrigatorio("Os campos Descricao ou link devem existir");
        }

        validarTamanhoDoTitulo(aviso.getTitulo());

        Date date=new java.util.Date();
        aviso.setDataDeCriacao(date);

        return avisoRepository.save(aviso);

    }

    private void validarTamanhoDoTitulo(String titulo) throws TamanhoDeCampoExcedente {
        if (titulo.length() > 300) throw new TamanhoDeCampoExcedente("Tamanho maximo de 300 caracteres");
    }

    public Page<Aviso> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return avisoRepository.findAll(pageRequest);
    }

    public Aviso update(Aviso aviso) throws CampoObrigatorio, TamanhoDeCampoExcedente {
        findById(aviso.getId());

        if (aviso.getTitulo() == null || (aviso.getDescricao() == null && aviso.getLinks().isEmpty())){
            throw new CampoObrigatorio("Os campos Descricao ou link devem existir");
        }

        validarTamanhoDoTitulo(aviso.getTitulo());

        return avisoRepository.save(aviso);
    }

    public void delete(Integer id) {

        avisoRepository.deleteById(id);

    }

    public List<Aviso> findAll(){
        return avisoRepository.findAll();
    }

}
