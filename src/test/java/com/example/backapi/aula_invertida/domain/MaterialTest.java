package com.example.backapi.aula_invertida.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MaterialTest {

    private Material material;
    private List<Link> links;

    @Before
    public void setUp() throws Exception {
        links = Arrays.asList(new Link(1, "www.internet.com"), new Link(2, "web.com"));
        material = new Material("Conteúdo extra de Matemática", "Este é um conteudo sobre geometria analita, para que possa auxiliar na prova",links );

    }

    @Test
    public void material_deve_conter_um_titulo() {
        String tituloEsperado = "Livro de quimica";
        material.setTitulo(tituloEsperado);

        String tituloRetornado = material.getTitulo();

        assertEquals(tituloEsperado, tituloRetornado);

    }

    @Test
    public void material_deve_conter_uma_descricao() {

        String descricaoEsperada = "Este livro de quimica é do terceiro ano do ensino medio";
        material.setDescricao(descricaoEsperada);

        String descricaoRetornado = material.getDescricao();

        assertEquals(descricaoEsperada, descricaoRetornado);
    }

    @Test
    public void material_deve_conter_uma_lista_de_links() {
        List<Link> linksEsperados = Arrays.asList(new Link(1, "www.internet.com"), new Link(2, "web.com"));
        material.setLinks(linksEsperados);

        List<Link> linksRetornados = material.getLinks();

        assertEquals(linksEsperados.size(), linksRetornados.size());


    }
}