package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AvisoServiceTest {
//
//    @Autowired
//    AvisoService avisoService;
//
//    @Test
//    public void administrador_quer_cadastrar_um_aviso() throws Exception {
//        //arrange
//        String titulo = "Halloween cancelado";
//        String descricao = "Guarde sua fantasia no armario, pois a popular festa de halloween foi cancelada";
//
//        Aviso avisoEsperado = new Aviso(titulo, descricao);
//
//        //action
//        Aviso avisoRecebido = avisoService.save(avisoEsperado);
//
//        //assert
//        assertEquals(avisoEsperado, avisoRecebido);
//
//
//    }
//
//    @Test(expected = ConstraintViolationException.class)
//    public void administrador_quer_criar_um_aviso_sem_descricao() throws Exception {
//        //arrange
//        String titulo = "Recesso escolar";
//
//        Aviso avisoEsperado = new Aviso();
//        avisoEsperado.setDescricao(titulo);
//
//        //action
//        Aviso avisoRecebido = avisoService.save(avisoEsperado);
//    }
//
//    @Test(expected = ConstraintViolationException.class)
//    public void administrador_quer_criar_um_aviso_sem_título() throws Exception {
//        //arrange
//        String descricao = "O Recesso escolar começa no próximo dia 22";
//        Aviso avisoEsperado = new Aviso();
//        avisoEsperado.setDescricao(descricao);
//
//        //action
//        Aviso avisoRecebido = avisoService.save(avisoEsperado);
//    }
//
//    @Test(expected = DataIntegrityViolationException.class)
//    public void administrador_quer_criar_um_aviso_com_titulo_com_mais_de_cem_caracteres() throws Exception {
//        //arrange
//        String titulo = "Realizaremos na quinta-feira a eleição da nova bandeira da escola, temos tres bandeiras candidatas, nao se esqueça de votar";
//        String descricao = "Eleição da bandeira";
//
//        Aviso avisoEsperado = new Aviso(titulo, descricao);
//
//        //action
//        Aviso avisoRecebido = avisoService.save(avisoEsperado);
//    }
//
//    @Test
//    public void aluno_quer_visualizar_lista_com_10_avisos(){
//        ArrayList<Aviso> avisosEsperados = new ArrayList<>();
//
//        //arrange
//        for(int i = 0; i < 10; i++){
//            Aviso aviso = new Aviso("Titulo", "Descricao");
//            avisoService.save(aviso);
//            avisosEsperados.add(aviso);
//        }
//
//        //action
//        Page<Aviso> avisosRetornados = avisoService.findPage(0, 10, "id", "ASC");
//        //assert
//
//        assertEquals(avisosEsperados.size() , avisosRetornados.getSize());
//
//    }
//
//    @Test
//    public void administrador_quer_deleter_um_aviso_por_id(){
//        Aviso aviso = new Aviso("Titulo", "Descricao");
//        avisoService.save(aviso);
//
//        Integer id = aviso.getId();
//        avisoService.delete(id);
//
//        List<Aviso> avisos = avisoService.findAll();
//
//        assertFalse(avisos.contains(aviso));
//
//    }
//
//    @Test
//    public void administrador_quer_visualizar_todos_os_avisos_cadastradas(){
//        ArrayList<Aviso> avisosEsperados = new ArrayList<Aviso>();
//
//        for (int i = 0; i < 10; i++){
//            Aviso aviso = new Aviso("Titulo", "Descricao");
//            avisoService.save(aviso);
//            avisosEsperados.add(aviso);
//        }
//
//        List<Aviso> avisosRetornados = avisoService.findAll();
//
//        for (int i = 0; i < avisosEsperados.size(); i++){
//            Aviso aviso = avisosEsperados.get(i);
//            assertTrue(avisosRetornados.contains(aviso));
//        }
//
//
//    }
//
//    @Test
//    public void lista_com_10_itens_da_sala_invertida_exibida_com_sucesso(){
//        ArrayList<Aviso> avisosEsperados = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++){
//            Aviso aviso = new Aviso("Titulo", "Descricao");
//            avisosEsperados.add(aviso);
//            avisoService.save(aviso);
//        }
//
//        Page<Aviso> avisosRetornados = avisoService.findPage(0, 10, "id", "ASC");
//
//        assertEquals(avisosEsperados.size() , avisosRetornados.getSize());
//
//    }
//
//    @Test
//    public void lista_com_mais_de_10_itens_da_sala_invertida_exibida_em_mais_de_uma_pagina(){
//        ArrayList<Aviso> avisosEsperados = new ArrayList<Aviso>();
//        for (int i = 0; i < 11; i++){
//            Aviso aviso = new Aviso("Titulo", "Descricao");
//            avisosEsperados.add(aviso);
//            avisoService.save(aviso);
//        }
//
//        Page<Aviso> avisosRetornados = avisoService.findPage(0, 10, "id", "ASC");
//
//        assert(avisosRetornados.getTotalPages() > 1);
//
//    }
//
//    @Test
//    public void administrador_quer_editar_um_aviso(){
//        Aviso aviso = new Aviso("Titulo", "Descricao");
//        avisoService.save(aviso);
//
//        Integer id = aviso.getId();
//
//        Aviso avisoEsperado = new Aviso("Titulo Editado", "Descricao Editado");
//
//        avisoEsperado.setId(id);
//        avisoService.save(avisoEsperado);
//
//        Aviso avisoRetornado = avisoService.findById(id);
//
//        assertEquals(avisoEsperado, avisoRetornado);
//    }

}