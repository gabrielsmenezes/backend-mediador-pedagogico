package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Link;
import com.example.backapi.aula_invertida.domain.Material;
import com.example.backapi.aula_invertida.domain.TipoDeLink;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MaterialServiceTest {

    @Autowired
    MaterialService materialService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void aluno_quer_visualizar_lista_de_materiais(){
        //arrange
        Date dataDeCriacao = new Date();

        Material material = new Material("Conteudo sobre matemática básica", "Este conteúdo é uma extensão extremamente importante", Arrays.asList(
            new Link(TipoDeLink.VIDEO.getCodigo(), "youtube.com")));
        materialService.save(material);

        //action
        Page<Material> retorno = materialService.findPage(0, 100, "id", "ASC");
        //assert

        assertTrue(retorno.get().anyMatch(material_da_lista -> {
            return material_da_lista.equals(material);
        }));

    }

    @Test
    public void lista_com_10_itens_da_sala_invertida_exibida_com_sucesso(){
        ArrayList<Material> materiaisEsperados = new ArrayList<Material>();

        for (int i = 0; i < 10; i++){
            Date dataDeCriacao = new Date();
            Material material = new Material("Titulo", "Descricao", Arrays.asList(
                    new Link(TipoDeLink.VIDEO.getCodigo(), "youtube.com")
            ));
            materiaisEsperados.add(material);
            materialService.save(material);
        }

        Page<Material> materiaisRetornados = materialService.findPage(0, 10, "id", "ASC");

        assertEquals(materiaisEsperados.size() , materiaisRetornados.getSize());

    }


    @Test
    public void lista_com_mais_de_10_itens_da_sala_invertida_exibida_em_mais_de_uma_pagina(){
        ArrayList<Material> materiaisEsperados = new ArrayList<Material>();
        Date dataDeCriacao = new Date();

        for (int i = 0; i < 11; i++){
            Material material = new Material("Titulo", "Descricao", Arrays.asList(
                    new Link(TipoDeLink.VIDEO.getCodigo(), "youtube.com")
            ));
            materiaisEsperados.add(material);
            materialService.save(material);
        }

        Page<Material> materiaisRetornados = materialService.findPage(0, 10, "id", "ASC");

        assert(materiaisRetornados.getTotalPages() > 1);

    }






}