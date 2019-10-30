package com.example.backapi.biblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class TopicoDTO implements Serializable {

    private Integer id;
    private String nome;
    @JsonIgnore
    private List<ItemTopico> links;
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
