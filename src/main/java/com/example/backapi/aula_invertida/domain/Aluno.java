package com.example.backapi.aula_invertida.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chaveDeAcesso;

    private String nomeDoAluno;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Aluno() {
    }

    public Aluno(String chaveDeAcesso, String nomeDoAluno, Turma turma) {
        this.chaveDeAcesso = chaveDeAcesso;
        this.nomeDoAluno = nomeDoAluno;
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

    public String getNomeDoAluno() {
        return nomeDoAluno;
    }

    public void setNomeDoAluno(String nomeDoAluno) {
        this.nomeDoAluno = nomeDoAluno;
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
