package com.example.backapi.bullying.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BullyingDTO {
    private Integer id;
    private String descricao;
    private String imagem;
    private String linkDoFormulario;
}
