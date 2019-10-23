package com.example.backapi.sobre_escola.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EscolaTest {

    private Escola escola;

    @Before
    public void setUp() throws Exception {
        escola = new Escola();
    }

    @Test
    public void deve_conter_id(){
        Integer idEsperado = 1;
        escola.setId(idEsperado);
        Integer idRetornado = escola.getId();
        assertEquals(idEsperado,idRetornado);
    }
    @Test
    public void deve_conter_nome(){
        String nomeEsperado = "Escola Estadual Dona Consuelo Muller";
        escola.setNome(nomeEsperado);
        String nomeRetornado = escola.getNome();
        assertEquals(nomeEsperado, nomeRetornado);
    }
    @Test
    public void deve_conter_imagem(){
        String imagemEsperada = "imagem";
        escola.setImagem(imagemEsperada);
        String imagemRetornada = escola.getImagem();
        assertEquals(imagemEsperada, imagemRetornada);
    }

    @Test
    public void deve_conter_descricao(){
        String decricaoEsperada = "descricao";
        escola.setDescricao(decricaoEsperada);
        String descricaoRetornada = escola.getDescricao();
        assertEquals(decricaoEsperada, descricaoRetornada);
    }
}