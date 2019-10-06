package com.example.backapi.aula_invertida.domain.material;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Material{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include private Integer id;

    @Column(length = 100)
    private String titulo;

    @Lob
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "links_material", joinColumns = @JoinColumn(name = "material_id"), foreignKey = @ForeignKey(name = "links_material_fk"))
    private List<LinkMaterial> links = new ArrayList<>();

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataDeCriacao;

    @Lob
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Material(String titulo, String descricao, List<LinkMaterial> links, String imagem, Turma turma) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.links = links;
        this.imagem = imagem;
        this.turma = turma;
    }

}
