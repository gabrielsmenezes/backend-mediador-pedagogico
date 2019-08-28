package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import org.hibernate.exception.DataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvisoServiceTest {

    @Autowired
    AvisoService avisoService;

    @Test
    public void administrador_quer_criar_um_aviso() throws Exception {
        //arrange
        String titulo = "Facom lanca foguete a lua";
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua. ";

        java.util.Date date=new java.util.Date();

        Aviso avisoEsperado = new Aviso(titulo, descricao, date);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);

        //assert
        assertEquals(avisoEsperado, avisoRecebido);


    }


    @Test(expected = TransactionSystemException.class)
    public void administrador_quer_criar_um_aviso_sem_descricao() throws Exception {
        //arrange
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua. ";
        Aviso avisoEsperado = new Aviso();
        avisoEsperado.setDescricao(descricao);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }

    @Test(expected = TransactionSystemException.class)
    public void administrador_quer_criar_um_aviso_sem_título() throws Exception {
        //arrange
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua. ";
        Aviso avisoEsperado = new Aviso();
        avisoEsperado.setDescricao(descricao);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void administrador_quer_criar_um_aviso_com_titulo_com_mais_de_cem_caracteres() throws Exception {
        //arrange
        String titulo = "Facom lanca foguete a lua a fim de descobrir se a linguagem de programacao Java e muito melhor ou pouco melhor que javascript";
        String descricao = "A Faculdade de Computacao da Universidadde Federal de Mato Grosso do Sul lanca foguete espacial com destido a lua. ";

        java.util.Date date=new java.util.Date();

        Aviso avisoEsperado = new Aviso(titulo, descricao, date);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }
}