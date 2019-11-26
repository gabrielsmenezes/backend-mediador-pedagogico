package com.example.backapi.sobre_escola.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ProfessorTest {

    Professor professor;

    @Before
    public void setUp() throws Exception {
        professor = new Professor();
    }

    @Test
    public void getId() {
        Integer idEsperado = 1;
        professor.setId(idEsperado);
        Integer idRetornado = professor.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void getNome() {
        String nomeEsperado = "Bruno Cafeo";
        professor.setNome(nomeEsperado);
        String nomeRetornado = professor.getNome();
        assertEquals(nomeEsperado, nomeRetornado);
    }

    @Test
    public void getDisciplinas() {
        List<String> disciplinasEsperada = Arrays.asList("Verificação, Validação e Teste II", "Manutenção de Software");
        professor.setDisciplinas(disciplinasEsperada);
        List<String> disciplinasRetornadas = professor.getDisciplinas();
        assertEquals(disciplinasEsperada, disciplinasRetornadas);
    }
}