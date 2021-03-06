package com.example.backapi.noticia.domain;

import org.junit.Before;
import org.junit.Test;

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

        noticia.setLinks(linkEsperado);

        String linkRetornado = noticia.getLinks();

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
    public void noticia_deve_ter_notificavel(){
        Noticia noticia = new Noticia();
        noticia.setNotificavel(true);
        assertEquals(true, noticia.isNotificavel());
    }

    @Test
    public void noticia_comparada_por_equals(){
        Noticia noticia = new Noticia();
        Noticia noticia2 = new Noticia();
        noticia.setId(1);
        noticia2.setId(2);
        assertNotEquals(noticia, noticia2);
    }
    @Test
    public void noticia_comparada_por_equals2(){
        Noticia noticia = new Noticia();
        noticia.setId(1);
        assertEquals(noticia, noticia);
    }

    @Test
    public void noticia_tem_hash(){
        Noticia noticia = new Noticia();
        Noticia noticia2 = new Noticia();
        noticia.setId(1);
        noticia2.setId(2);
        assertNotEquals(noticia.hashCode(), noticia2.hashCode());
    }

}