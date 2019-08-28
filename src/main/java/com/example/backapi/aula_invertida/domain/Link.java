package com.example.backapi.aula_invertida.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Link {
    @Column
    private int tipoDeLink;

    @Column
    private String link;

    public Link() {
    }

    public Link(int tipoDeLink, String link) {
        this.tipoDeLink = tipoDeLink;
        this.link = link;
    }

    public int getTipoDeLink() {
        return tipoDeLink;
    }

    public void setTipoDeLink(int tipoDeLink) {
        this.tipoDeLink = tipoDeLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
