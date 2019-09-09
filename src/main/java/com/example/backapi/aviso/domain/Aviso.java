package com.example.backapi.aviso.domain;

import com.example.backapi.recurso.domain.Link;
import com.example.backapi.recurso.domain.Recurso;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Aviso extends Recurso {

    public Aviso(){}

    public Aviso(String titulo, String descricao, List<Link> links, String imagem) {
        super(titulo, descricao, links, imagem);
    }
}