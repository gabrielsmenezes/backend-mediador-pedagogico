package com.example.backapi.recurso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance
public abstract class Recurso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String titulo;

    @Column
    @NotNull
    private String descricao;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "links", joinColumns = @JoinColumn(name = "recurso_id"), foreignKey = @ForeignKey(name = "links_recurso_fk"))
    private List<Link> links = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;

    @Lob
    private String imagem;

    public Recurso() {
    }

    public Recurso(String titulo, String descricao, List<Link> links, String imagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.links = links;
        this.imagem = imagem;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recurso = (Recurso) o;
        return id.equals(recurso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
}
