package com.example.backapi.recurso.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkTest {

    @Test
    public void link_deve_ter_nome(){

        Link link = new Link();

        String linkEsperado = "www.internet.com";
        link.setLink(linkEsperado);

        assertEquals(linkEsperado, link.getLink());

    }

    @Test
    public void link_deve_ter_link(){
        Link link = new Link();

        String nome = "Video-aula sobre anatomia";

        link.setNome(nome);

        assertEquals(nome, link.getNome());

    }

}