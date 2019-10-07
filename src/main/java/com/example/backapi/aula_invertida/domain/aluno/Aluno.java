package com.example.backapi.aula_invertida.domain.aluno;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @JsonIgnore
    private String chaveDeAcesso;

    private String nome;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

}
