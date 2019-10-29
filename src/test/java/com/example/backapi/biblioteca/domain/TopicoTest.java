package com.example.backapi.biblioteca.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TopicoTest {

    Topico topico;

    @Before
    public void setUp() throws Exception {
        topico = new Topico();
    }

    @Test
    public void getId() {
        Integer idesperado = 1;
        topico.setId(idesperado);
        Integer idObtido = topico.getId();
        assertEquals(idesperado, idObtido);
    }

    @Test
    public void getNome() {
        String nomeEsperado = "Livros";
        topico.setNome(nomeEsperado);
        String nomeObtido = topico.getNome();
        assertEquals(nomeEsperado, nomeObtido);
    }
}