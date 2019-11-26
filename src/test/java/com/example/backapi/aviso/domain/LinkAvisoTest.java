package com.example.backapi.aviso.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkAvisoTest {

    private LinkAviso link;

    @Before
    public void setUp() throws Exception {
        link = new LinkAviso();
    }

    @Test
    public void link_deve_ter_link() {
        String linkEsperado = "www.google.com";
        link.setLink(linkEsperado);
        String linkRetornado = link.getLink();
        assertEquals(linkEsperado,linkRetornado);
    }

    @Test
    public void link_deve_ter_nome() {
        String nomeEsperado = "Link do google";
        link.setNome(nomeEsperado);
        String nomeRetornado = link.getNome();
        assertEquals(nomeEsperado, nomeRetornado);
    }
}