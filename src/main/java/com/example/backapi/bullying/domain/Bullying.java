package com.example.backapi.bullying.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bullying{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @Column
    @Lob
    private String descricao;

    @Column
    @Lob
    private String imagem;

    @Column
    private String linkDoFormulario;

}
