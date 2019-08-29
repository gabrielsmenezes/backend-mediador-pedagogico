package com.example.backapi.aviso.services;

import com.example.backapi.aviso.domain.Aviso;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvisoServiceTest {

    @Autowired
    AvisoService avisoService;

    @Test
    public void administrador_quer_cadastrar_um_aviso() throws Exception {
        //arrange
        String titulo = "Halloween cancelado";
        String descricao = "Guarde sua fantasia no armario, pois a popular festa de halloween foi cancelada";

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
        String titulo = "Recesso escolar";

        Aviso avisoEsperado = new Aviso();
        avisoEsperado.setDescricao(titulo);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }

    @Test(expected = TransactionSystemException.class)
    public void administrador_quer_criar_um_aviso_sem_título() throws Exception {
        //arrange
        String descricao = "O Recesso escolar começa no próximo dia 22";
        Aviso avisoEsperado = new Aviso();
        avisoEsperado.setDescricao(descricao);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void administrador_quer_criar_um_aviso_com_titulo_com_mais_de_cem_caracteres() throws Exception {
        //arrange
        String titulo = "Realizaremos na quinta-feira a eleição da nova bandeira da escola, temos tres bandeiras candidatas, nao se esqueça de votar";
        String descricao = "Eleição da bandeira";

        java.util.Date date=new java.util.Date();

        Aviso avisoEsperado = new Aviso(titulo, descricao, date);

        //action
        Aviso avisoRecebido = avisoService.save(avisoEsperado);
    }
}