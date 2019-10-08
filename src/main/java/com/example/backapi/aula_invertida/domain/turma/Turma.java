package com.example.backapi.aula_invertida.domain.turma;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.material.Material;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Turma implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    private String nome;

    @Column
    private String chaveDeAcesso;

    @JsonIgnore
    @OneToMany(mappedBy = "turma")
    private List<Material> materiais = new ArrayList<>();


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "turma")
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
}
