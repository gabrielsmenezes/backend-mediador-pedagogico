package com.example.backapi.gremio.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Gremio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String link;
    @Lob
    private String imagem;
    @Lob
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gremio gremio = (Gremio) o;
        return id.equals(gremio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
