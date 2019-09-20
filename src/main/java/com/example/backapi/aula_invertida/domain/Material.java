package com.example.backapi.aula_invertida.domain;

import com.example.backapi.recurso.domain.Link;
import com.example.backapi.recurso.domain.Recurso;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Material extends Recurso {

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Material(){}

    public Material(String titulo, String descricao, List<Link> links, String imagem) {
        super(titulo, descricao, links, imagem);
    }
}
