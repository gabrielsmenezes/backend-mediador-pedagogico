package com.example.backapi.sobre_escola.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EscolaDTOTest {

    EscolaDTO escolaDTO;

    @Before
    public void setUp() throws Exception {
        escolaDTO = new EscolaDTO();
    }

    @Test
    public void deve_conter_id(){
        Integer idEsperado = 1;
        escolaDTO.setId(idEsperado);
        Integer idRetornado = escolaDTO.getId();
        assertEquals(idEsperado,idRetornado);
    }
    @Test
    public void deve_conter_nome(){
        String nomeEsperado = "Escola Estadual Dona Consuelo Muller";
        escolaDTO.setNome(nomeEsperado);
        String nomeRetornado = escolaDTO.getNome();
        assertEquals(nomeEsperado, nomeRetornado);
    }
    @Test
    public void deve_conter_imagem(){
        String imagemEsperada = "imagem";
        escolaDTO.setImagem(imagemEsperada);
        String imagemRetornada = escolaDTO.getImagem();
        assertEquals(imagemEsperada, imagemRetornada);
    }

    @Test
    public void deve_conter_descricao(){
        String decricaoEsperada = "descricao";
        escolaDTO.setDescricao(decricaoEsperada);
        String descricaoRetornada = escolaDTO.getDescricao();
        assertEquals(decricaoEsperada, descricaoRetornada);
    }

}