package com.example.backapi.sobre_escola.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EscolaDTO {
    private Integer id;
    private String nome;
    private String imagem;
    private String descricao;
}
