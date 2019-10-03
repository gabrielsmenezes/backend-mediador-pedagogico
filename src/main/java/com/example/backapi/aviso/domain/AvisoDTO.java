package com.example.backapi.aviso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AvisoDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private List<LinkAviso> links;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private String imagem;

    public AvisoDTO() {
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

    public List<LinkAviso> getLinks() {
        return links;
    }

    public void setLinks(List<LinkAviso> links) {
        this.links = links;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoDTO avisoDTO = (AvisoDTO) o;
        return id.equals(avisoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}