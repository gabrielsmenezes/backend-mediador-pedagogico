package com.example.backapi.aula_invertida.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Link {
    @Column
    private String link;

    @Column
    private String nome;

    public Link() {
    }

    public Link(String link, String nome) {
        this.link = link;
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
