package com.example.backapi.aula_invertida.domain.turma;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TurmaDTOTest {

    private TurmaDTO turmaDTO;

    @Before
    public void setUp() throws Exception {
        turmaDTO = new TurmaDTO();
    }

    @Test
    public void turma_deve_ter_id() {
        Integer idEsperado = 1;
        turmaDTO.setId(idEsperado);
        Integer idRetornado = turmaDTO.getId();
        assertEquals(idEsperado, idRetornado);
    }

    @Test
    public void turma_deve_ter_nome() {
        String nomeEsperado = "2C";
        turmaDTO.setNome(nomeEsperado);
        String nomeRetornado = turmaDTO.getNome();
        assertEquals(nomeEsperado, nomeRetornado);
    }

    @Test
    public void turma_deve_ter_chave_de_acesso() {
        String chaveDeAcessoEsperado = "chave";
        turmaDTO.setChaveDeAcesso(chaveDeAcessoEsperado);
        String chaveDeAcessoRetornado = turmaDTO.getChaveDeAcesso();
        assertEquals(chaveDeAcessoEsperado, chaveDeAcessoRetornado);
    }

    @Test
    public void turma_deve_ser_comparada_por_id() {
        turmaDTO.setId(1);
        TurmaDTO turmaDTO2 = new TurmaDTO();
        turmaDTO2.setId(1);
        assertEquals(turmaDTO2, turmaDTO);

    }
}