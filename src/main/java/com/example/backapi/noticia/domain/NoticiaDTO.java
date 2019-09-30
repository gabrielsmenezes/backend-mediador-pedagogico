package com.example.backapi.noticia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class NoticiaDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private String links;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private Boolean notificavel;

    public NoticiaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Boolean isNotificavel() {
        return notificavel;
    }

    public void setNotificavel(Boolean notificavel) {
        this.notificavel = notificavel;
    }
}
