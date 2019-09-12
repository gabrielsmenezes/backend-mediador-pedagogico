package com.example.backapi.turma.domain;

import com.example.backapi.aula_invertida.domain.Material;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String chaveDeAcesso;

    @ElementCollection
    @CollectionTable(name = "materiais", joinColumns = @JoinColumn(name = "turma_id"), foreignKey = @ForeignKey(name = "material_turma_fk"))
    private List<Material> materiais = new ArrayList<>();


    public Turma() {
    }

    public Turma(String nome, String chaveDeAcesso, List<Material>materiais) {
        this.nome = nome;
        this.chaveDeAcesso = chaveDeAcesso;
        this.materiais = materiais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChaveDeAcesso() {
        return chaveDeAcesso;
    }

    public void setChaveDeAcesso(String chaveDeAcesso) {
        this.chaveDeAcesso = chaveDeAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return id.equals(turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }
}