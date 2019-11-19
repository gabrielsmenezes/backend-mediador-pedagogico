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
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AvisoListener.class)
public class Aviso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String titulo;

    @Lob
    @Column
    private String descricao;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviso aviso = (Aviso) o;
        return id.equals(aviso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}