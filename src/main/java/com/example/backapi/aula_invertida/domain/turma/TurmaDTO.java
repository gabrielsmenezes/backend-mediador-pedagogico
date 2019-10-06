package com.example.backapi.aula_invertida.domain.turma;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TurmaDTO implements Serializable {

    @EqualsAndHashCode.Include private Integer id;
    private String nome;
    private String chaveDeAcesso;
}
