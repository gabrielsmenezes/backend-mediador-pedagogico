package com.example.backapi.bullying.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bullying{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Lob
    private String descricao;

    @Column
    @Lob
    private String imagem;

    @Column
    private String linkDoFormulario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bullying bullying = (Bullying) o;
        return id.equals(bullying.id);
    }

}
