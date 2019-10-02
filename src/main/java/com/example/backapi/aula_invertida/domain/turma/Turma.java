package com.example.backapi.aula_invertida.domain.turma;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.material.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "turma")
    private List<Material> materiais = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "turma")
    private List<Aluno> alunos = new ArrayList<>();

    @JsonIgnore
    @ElementCollection
    private List<String> ultimasChaves = new ArrayList<>();

    public Turma() {
    }

    public Turma(String nome, String chaveDeAcesso, List<Material>materiais, List<Aluno> alunos) {
        this.nome = nome;
        this.chaveDeAcesso = chaveDeAcesso;
        this.materiais = materiais;
        this.alunos = alunos;
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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void adicionarNovaChave(String novaChave) {
        if (ultimasChaves.size() < 2){
            ultimasChaves.add(novaChave);
        } else {
            ultimasChaves.remove(0);
            ultimasChaves.add(novaChave);
        }
    }


    public boolean chaveFoiUsadaRecentemente(String chaveDeAcesso) {
        return ultimasChaves.contains(chaveDeAcesso);
    }

    public List<String> getUltimasChaves() {
        return ultimasChaves;
    }

    public void setUltimasChaves(List<String> ultimasChaves) {
        this.ultimasChaves = ultimasChaves;
    }
}
