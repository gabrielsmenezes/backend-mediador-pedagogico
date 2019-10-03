package com.example.backapi.aula_invertida.domain.turma;

import java.io.Serializable;
import java.util.Objects;

public class TurmaDTO implements Serializable {

    private Integer id;
    private String nome;
    private String chaveDeAcesso;

    public TurmaDTO() {
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
        TurmaDTO turmaDTO = (TurmaDTO) o;
        return id.equals(turmaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
