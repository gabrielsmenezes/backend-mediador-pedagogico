package com.example.backapi.calendario.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarioTest {

    private Calendario calendario;

    @Before
    public void setUp() throws Exception {
        calendario = new Calendario();
    }

    @Test
    public void calendario_deve_ter_link(){
        String linkEsperado = "www.google.com";
        calendario.setLinkDoCalendario(linkEsperado);
        String linkRetornado = calendario.getLinkDoCalendario();
        assertEquals(linkEsperado, linkRetornado);
    }

    @Test
    public void calendario_deve_ter_id(){
        Integer idEsperado = 1;
        calendario.setId(idEsperado);
        Integer idRetornado = calendario.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void calendario_deve_ter_hash(){
        assertEquals(calendario.hashCode(), calendario.hashCode());
    }

    @Test
    public void calendario_deve_ser_comparada_pelo_id(){
        Calendario calendario2 = new Calendario();
        calendario2.setId(1);
        calendario.setId(1);
        assertEquals(calendario, calendario2);
    }
}