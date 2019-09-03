package com.example.backapi.aviso.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvisoTest {

    @Test
    public void todo_aviso_deve_conter_titulo() {
        //arrange
        String titulo = "Facom lanca foguete a lua";
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua. ";

        java.util.Date date=new java.util.Date();


        Aviso aviso = new Aviso(titulo, descricao);

        //action
        String tituloObtido = aviso.getTitulo();

        //assert
        assertEquals (titulo, tituloObtido);

    }

    @Test
    public void todo_aviso_deve_conter_descricao(){
        String titulo = "Facom lanca foguete a lua";
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua.";

        java.util.Date date=new java.util.Date();


        Aviso aviso = new Aviso(titulo, descricao);

        //action
        String descricaoObtida = aviso.getDescricao();

        //assert
        assertEquals (descricao, descricaoObtida);
    }

}