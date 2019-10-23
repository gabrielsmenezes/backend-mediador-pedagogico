package com.example.backapi.sobre_escola.domain;

import com.example.backapi.aviso.domain.LinkAviso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "disciplinas_professor", joinColumns = @JoinColumn(name = "professor_id"), foreignKey = @ForeignKey(name = "disciplinas_professor_fk"))
    private List<String> disciplinas = new ArrayList<>();

    @Lob
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id.equals(professor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
