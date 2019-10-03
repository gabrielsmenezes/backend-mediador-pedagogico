package com.example.backapi.aviso.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Aviso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String titulo;

    @Column
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "links_aviso", joinColumns = @JoinColumn(name = "aviso_id"), foreignKey = @ForeignKey(name = "links_aviso_fk"))
    private List<LinkAviso> links = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;

    @Lob
    private String imagem;

    public Aviso(){}

    public Aviso(String titulo, String descricao, List<LinkAviso> links, String imagem) {
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

    public List<LinkAviso> getLinks() {
        return links;
    }

    public void setLinks(List<LinkAviso> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviso aviso = (Aviso) o;
        return id.equals(aviso.id);
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