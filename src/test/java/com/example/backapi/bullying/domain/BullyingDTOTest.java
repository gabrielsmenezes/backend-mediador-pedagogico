package com.example.backapi.bullying.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BullyingDTOTest {

    BullyingDTO bullyingDTO;

    @Before
    public void setUp() throws Exception {
        bullyingDTO = new BullyingDTO();
    }

    @Test
    public void deve_conter_id(){
        Integer idEsperado = 1;
        bullyingDTO.setId(idEsperado);
        Integer idRetornado = bullyingDTO.getId();
        assertEquals(idEsperado,idRetornado);
    }
    @Test
    public void deve_conter_descricao(){
        String descricaoEsperada = "Bullying é a prática de atos violentos, intencionais e repetidos, contra uma pessoa indefesa, que podem causar danos físicos e psicológicos às vítimas. O termo surgiu a partir do inglês bully\u200B, palavra que significa tirano, brigão ou valentão, na tradução para o português";
        bullyingDTO.setDescricao(descricaoEsperada);
        String descricaoRetornada = bullyingDTO.getDescricao();
        assertEquals(descricaoEsperada, descricaoRetornada);
    }
    @Test
    public void deve_conter_linkDoFormulario(){
        String linkEsperado = "www.form.google.com";
        bullyingDTO.setLinkDoFormulario(linkEsperado);
        String linkRetornado = bullyingDTO.getLinkDoFormulario();
        assertEquals(linkEsperado, linkRetornado);
    }
}