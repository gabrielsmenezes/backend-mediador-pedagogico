package com.example.backapi.aula_invertida.domain.turma;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TurmaDTO implements Serializable {

    private Integer id;
    private String nome;
    private String chaveDeAcesso;

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
