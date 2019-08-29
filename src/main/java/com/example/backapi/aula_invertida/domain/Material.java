package com.example.backapi.aula_invertida.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Material implements Serializable {

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
    @CollectionTable(name = "links", joinColumns = @JoinColumn(name = "material_id"), foreignKey = @ForeignKey(name = "links_material_fk"))
    private List<Link> links = new ArrayList<>();

    public Material() {
    }

    public Material(String titulo, String descricao, List<Link> links) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.links = links;
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
        Material material = (Material) o;
        return id.equals(material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
