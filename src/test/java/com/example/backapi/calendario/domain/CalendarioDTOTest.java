package com.example.backapi.calendario.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarioDTOTest {

    CalendarioDTO calendarioDTO;

    @Before
    public void setUp() throws Exception {
        calendarioDTO = new CalendarioDTO();
    }

    @Test
    public void calendarioDTO_deve_ter_id(){
        Integer idEsperado = 1;
        calendarioDTO.setId(idEsperado);
        Integer idRetornado = calendarioDTO.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void calendarioDTO_deve_link(){
        String linkEsperado = "www.google.com";
        calendarioDTO.setLinkDoCalendario(linkEsperado);
        String linkRetornado = calendarioDTO.getLinkDoCalendario();
        assertEquals(linkEsperado, linkRetornado);
    }

}