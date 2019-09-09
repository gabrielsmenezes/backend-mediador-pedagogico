package com.example.backapi.aula_invertida.domain;

import com.example.backapi.recurso.domain.Link;
import com.example.backapi.recurso.domain.Recurso;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Material extends Recurso {

    public Material(){}

    public Material(String titulo, String descricao, List<Link> links, String imagem) {
        super(titulo, descricao, links, imagem);
    }
}
