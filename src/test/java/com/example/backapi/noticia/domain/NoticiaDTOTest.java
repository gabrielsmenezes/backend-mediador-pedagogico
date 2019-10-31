package com.example.backapi.noticia.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoticiaDTOTest {

    NoticiaDTO noticiaDTO;

    @Before
    public void setUp() throws Exception {
        noticiaDTO = new NoticiaDTO();
        noticiaDTO.setTitulo("Titulo");
        noticiaDTO.setDescricao("Descricao");
        noticiaDTO.setLinks("link");
        noticiaDTO.setNotificavel(true);
    }

    @Test
    public void isNotificavel() {
        assertEquals(true, noticiaDTO.isNotificavel());
    }

    @Test
    public void getNotificavel() {
        assertEquals(true, noticiaDTO.getNotificavel());
    }

    @Test
    public void equals(){
        noticiaDTO.setId(0);
        NoticiaDTO noticiaDTO2 = new NoticiaDTO();
        noticiaDTO2.setId(0);
        assertEquals(noticiaDTO2, noticiaDTO);
    }

}