package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Material;
import com.example.backapi.aula_invertida.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import java.util.Date;

@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    public Material save(Material material){
        Date date=new java.util.Date();
        material.setDataDeCriacao(date);
        return materialRepository.save(material);
    }

    public Page<Material> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
            PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
            return materialRepository.findAll(pageRequest);
    }
}
