package com.example.backapi.sobre_escola.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class EscolaDTO {
    private Integer id;
    private String nome;
    private String imagem;
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscolaDTO escolaDTO = (EscolaDTO) o;
        return id.equals(escolaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
