package com.example.backapi.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicoDTOTest {

    TopicoDTO topicoDTO;

    @Before
    public void setUp() throws Exception {
        topicoDTO = new TopicoDTO();
    }

    @Test
    public void getId() {
        Integer idEsperado = 1;
        topicoDTO.setId(idEsperado);
        Integer idObtido = topicoDTO.getId();
        assertEquals(idEsperado, idObtido);
    }

    @Test
    public void getNome() {
        String esperado = "Video-aulas";
        topicoDTO.setNome(esperado);
        String obtido = topicoDTO.getNome();
        assertEquals(esperado, obtido);
    }
}