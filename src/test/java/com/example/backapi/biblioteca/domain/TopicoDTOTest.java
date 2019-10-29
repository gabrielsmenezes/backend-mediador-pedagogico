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

    @Test
    public void iquals(){
        topicoDTO.setId(1);
        TopicoDTO topicoDTO2 = new TopicoDTO();
        topicoDTO2.setId(1);
        assertEquals(topicoDTO, topicoDTO2);
    }
}