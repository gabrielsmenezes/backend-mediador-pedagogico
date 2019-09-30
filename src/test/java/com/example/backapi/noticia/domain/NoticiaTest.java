package com.example.backapi.noticia.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class NoticiaTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void noticia_deve_ter_titulo(){
        String tituloEsperado = "MEC divulga datas do Enem";

        Noticia noticia = new Noticia();

        noticia.setTitulo(tituloEsperado);

        String tituloRetornado = noticia.getTitulo();

        assertEquals(tituloEsperado, tituloRetornado);

    }

    @Test
    public void noticia_deve_ter_descricao(){
        String descricaoEsperado = "Enem será realizado dias 10 e 17 de outubro";

        Noticia noticia = new Noticia();

        noticia.setDescricao(descricaoEsperado);

        String descricaoRetornado = noticia.getDescricao();

        assertEquals(descricaoEsperado, descricaoRetornado);
    }

    @Test
    public void noticia_deve_ter_links(){
        String linkEsperado = "www.enem.com.br";

        Noticia noticia = new Noticia();

        noticia.setLink(linkEsperado);

        String linkRetornado = noticia.getLink();

        assertEquals(linkEsperado, linkRetornado);
    }

    @Test
    public void noticia_deve_ter_dataDeCriacao(){
        Date dataDeCriacaoEsperada = new Date();

        Noticia noticia = new Noticia();

        noticia.setDataDeCriacao(dataDeCriacaoEsperada);

        Date dataDeCriacaoRetornada = noticia.getDataDeCriacao();

        assertEquals(dataDeCriacaoEsperada, dataDeCriacaoRetornada);
    }

    @Test
    public void noticia_deve_ter_notificavel(){}
}