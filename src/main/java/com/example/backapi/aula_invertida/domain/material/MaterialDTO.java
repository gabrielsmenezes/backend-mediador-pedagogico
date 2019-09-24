package com.example.backapi.aula_invertida.domain.material;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MaterialDTO implements Serializable {

    private Integer id;
    private String titulo;
    private String descricao;
    private List<LinkMaterial> links;
    private Date dataDeCriacao;
    private String imagem;
    private Integer turma_id;


    public MaterialDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<LinkMaterial> getLinks() {
        return links;
    }

    public void setLinks(List<LinkMaterial> links) {
        this.links = links;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(Integer turma_id) {
        this.turma_id = turma_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDTO that = (MaterialDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}