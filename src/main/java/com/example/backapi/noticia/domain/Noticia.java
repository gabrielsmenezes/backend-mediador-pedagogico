package com.example.backapi.noticia.domain;

import com.example.backapi.notificacao.listener.NoticiaListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(NoticiaListener.class)
public class Noticia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @Column
    private String titulo;

    @Lob
    @Column
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
