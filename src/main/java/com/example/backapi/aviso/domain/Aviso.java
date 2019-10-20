package com.example.backapi.aviso.domain;

import com.example.backapi.notificacao.listener.AvisoListener;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AvisoListener.class)
public class Aviso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @Column
    private String titulo;

    @Lob
    @Column
    private String descricao;
    //TODO: retirar o EAGER para ficar LAZY, a solucao encontrada no service do material (findpage)

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "links_aviso", joinColumns = @JoinColumn(name = "aviso_id"), foreignKey = @ForeignKey(name = "links_aviso_fk"))
    private List<LinkAviso> links = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;

    @Lob
    private String imagem;

    public Aviso(String titulo, String descricao, List<LinkAviso> links, String imagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.links = links;
        this.imagem = imagem;
    }
}