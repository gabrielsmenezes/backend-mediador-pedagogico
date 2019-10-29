package com.example.backapi.biblioteca.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TopicoDTO implements Serializable {

    private Integer id;
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicoDTO topicoDTO = (TopicoDTO) o;
        return id.equals(topicoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
