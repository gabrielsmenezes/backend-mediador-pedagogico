package com.example.backapi.noticia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class NoticiaDTO implements Serializable {
    private Integer id;
    private String titulo;
    private String descricao;
    private String links;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private Boolean notificavel;

    public Boolean isNotificavel() {
        return notificavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticiaDTO that = (NoticiaDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
