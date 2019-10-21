package com.example.backapi.gremio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GremioDTO {
    private Integer id;
    private String linkDoGremio;
    private String imagem;
    private String descricao;
}
