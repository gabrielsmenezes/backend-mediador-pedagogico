package com.example.backapi.aula_invertida.domain.material;

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
public class MaterialDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String descricao;
    private List<LinkMaterial> links;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private String imagem;
    private Integer turmaId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDTO that = (MaterialDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}