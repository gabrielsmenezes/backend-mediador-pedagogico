package com.example.backapi.gremio.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GremioTest {

    Gremio gremio;

    @Before
    public void setUp() throws Exception {
        gremio = new Gremio();
    }

    @Test
    public void deve_ter_id() {
        Integer idEsperado = 1;
        gremio.setId(idEsperado);
        Integer idRetornado = gremio.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void deve_ter_link() {
        String linkEsperado = "www.esperado.com";
        gremio.setLinkDoGremio(linkEsperado);
        String linkRetornado = gremio.getLinkDoGremio();
        assertEquals(linkEsperado, linkRetornado);
    }

    @Test
    public void deve_ter_imagem() {
        String imagemEsperada = "imagem";
        gremio.setImagem(imagemEsperada);
        String imagemRetornada = gremio.getImagem();
        assertEquals(imagemEsperada, imagemRetornada);
    }

    @Test
    public void deve_ter_descricao() {
        String descricaoEsperada = "descricao";
        gremio.setDescricao(descricaoEsperada);
        String descricaoRetornada = gremio.getDescricao();
        assertEquals(descricaoEsperada, descricaoRetornada);
    }
}