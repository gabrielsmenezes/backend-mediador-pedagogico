package com.example.backapi.aula_invertida.domain.turma;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.material.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Turma implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String chaveDeAcesso;

    @JsonIgnore
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<Material> materiais = new ArrayList<>();

    //TODO: retirar o EAGER para ficar LAZY, a solucao encontrada no service do material (findpage)
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "turma", cascade = CascadeType.REMOVE)
    private List<Aluno> alunos = new ArrayList<>();

    @JsonIgnore
    @ElementCollection
    private List<String> ultimasChaves = new ArrayList<>();

    public Turma(String nome, String chaveDeAcesso, List<Material> materiais, List<Aluno> alunos) {
        this.nome = nome;
        this.chaveDeAcesso = chaveDeAcesso;
        this.materiais = materiais;
        this.alunos = alunos;
    }

    public void adicionarNovaChave(String novaChave) {
        if (ultimasChaves.size() < 2) {
            ultimasChaves.add(novaChave);
        } else {
            ultimasChaves.remove(0);
            ultimasChaves.add(novaChave);
        }
    }

    public boolean chaveFoiUsadaRecentemente(String chaveDeAcesso) {
        return ultimasChaves.contains(chaveDeAcesso);
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
}
