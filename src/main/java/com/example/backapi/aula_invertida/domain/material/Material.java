package com.example.backapi.aula_invertida.domain.material;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String titulo;

    @Column
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "links_material", joinColumns = @JoinColumn(name = "material_id"), foreignKey = @ForeignKey(name = "links_material_fk"))
    private List<LinkMaterial> links = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;

    @Lob
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Material(){}

    public Material(String titulo, String descricao, List<LinkMaterial> links, String imagem) {

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

    public List<LinkMaterial> getLinks() {
        return links;
    }

    public void setLinks(List<LinkMaterial> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return id.equals(material.id);
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
