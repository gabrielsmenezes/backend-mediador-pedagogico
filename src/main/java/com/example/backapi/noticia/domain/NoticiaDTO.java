package com.example.backapi.noticia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class NoticiaDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private String link;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private boolean notificavel;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public boolean isNotificavel() {
        return notificavel;
    }

    public void setNotificavel(boolean notificavel) {
        this.notificavel = notificavel;
    }
}
