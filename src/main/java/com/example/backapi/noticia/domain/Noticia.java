package com.example.backapi.noticia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Noticia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @Column(length = 50)
    private String titulo;

    @Column(length = 100)
    private String descricao;

    @Column
    private String links;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column
    private Date dataDeCriacao;

    @Column
    private boolean notificavel;

    public boolean isNotificavel() {
        return notificavel;
    }

}
