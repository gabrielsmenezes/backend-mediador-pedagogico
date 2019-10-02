package com.example.backapi.aula_invertida.domain.aluno;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    private String chaveDeAcesso;

    private String nome;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Aluno() {
    }

    public Aluno(String chaveDeAcesso, String nome, Turma turma) {
        this.chaveDeAcesso = chaveDeAcesso;
        this.nome = nome;
        this.turma = turma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChaveDeAcesso() {
        return chaveDeAcesso;
    }

    public void setChaveDeAcesso(String chaveDeAcesso) {
        this.chaveDeAcesso = chaveDeAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
