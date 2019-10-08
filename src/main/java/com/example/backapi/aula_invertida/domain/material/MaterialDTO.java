package com.example.backapi.aula_invertida.domain.material;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MaterialDTO implements Serializable {

    @EqualsAndHashCode.Include private Integer id;
    private String titulo;
    private String descricao;
    private List<LinkMaterial> links;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;
    private String imagem;
    private Integer turmaId;

}