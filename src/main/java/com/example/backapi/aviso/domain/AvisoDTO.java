package com.example.backapi.aviso.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
public class AvisoDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String descricao;
    private List<LinkAviso> links;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private String imagem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoDTO avisoDTO = (AvisoDTO) o;
        return id.equals(avisoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}