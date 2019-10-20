package com.example.backapi.gremio.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GremioDTOTest {

    GremioDTO gremioDTO;

    @Before
    public void setUp() throws Exception {
        gremioDTO = new GremioDTO();
    }

    @Test
    public void deve_ter_id() {
        Integer idEsperado = 1;
        gremioDTO.setId(idEsperado);
        Integer idRetornado = gremioDTO.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void deve_ter_link() {
        String linkEsperado = "www.esperado.com";
        gremioDTO.setLink(linkEsperado);
        String linkRetornado = gremioDTO.getLink();
        assertEquals(linkEsperado, linkRetornado);
    }

    @Test
    public void deve_ter_imagem() {
        String imagemEsperada = "imagem";
        gremioDTO.setImagem(imagemEsperada);
        String imagemRetornada = gremioDTO.getImagem();
        assertEquals(imagemEsperada, imagemRetornada);
    }

    @Test
    public void deve_ter_descricao() {
        String descricaoEsperada = "descricao";
        gremioDTO.setDescricao(descricaoEsperada);
        String descricaoRetornada = gremioDTO.getDescricao();
        assertEquals(descricaoEsperada, descricaoRetornada);
    }
}