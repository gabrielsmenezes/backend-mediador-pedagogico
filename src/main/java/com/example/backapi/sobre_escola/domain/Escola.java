package com.example.backapi.sobre_escola.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    @Lob
    private String imagem;

    @Column
    @Lob
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escola escola = (Escola) o;
        return id.equals(escola.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
