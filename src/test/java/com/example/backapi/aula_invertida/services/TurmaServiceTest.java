package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class TurmaServiceTest {

    @Autowired
    TurmaService turmaService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void administrador_criar_uma_turma_com_nome_da_turma_e_chave_de_acesso(){
        String nomeDaTurma = "3° B";
        String chaveDeAcesso = "terceiroB";

        Turma turmaEsperada = new Turma();
        turmaEsperada.setNome(nomeDaTurma);
        turmaEsperada.setChaveDeAcesso(chaveDeAcesso);

        Turma turmaRetornada = turmaService.save(turmaEsperada);

        assertEquals(turmaEsperada, turmaRetornada);

    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_sem_nome(){
        Turma turma = new Turma(null, "segundoA", null, null);

        turmaService.save(turma);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_turma_sem_chave_de_acesso_da_turma(){
        Turma turma;
        turma = new Turma("2°A", null, null, null);

        turmaService.save(turma);

    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_o_nome_ja_existente(){
        turmaService.save(new Turma("2A", "abobora", null, null));

        turmaService.save(new Turma("2A", "abacaxi", null, null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_chave_de_acesso_existente(){

        turmaService.save(new Turma("2C", "abacaxi", null, null));

        turmaService.save(new Turma("2A", "abacaxi", null, null));

    }

    @Test
    public void administrador_lista_as_turmas_com_sucesso(){
        Turma turma = new Turma();
        turma.setNome("2B");
        turma.setChaveDeAcesso("cebola");

        turma = turmaService.save(turma);

        List<Turma> turmas = turmaService.findAll();

        assertTrue(turmas.contains(turma));
    }

    @Test
    public void administrador_quer_deletar_uma_turma() throws ObjetoNaoEncontrado {
        Turma turma = new Turma();
        turma.setNome("2B");
        turma.setChaveDeAcesso("cebola");

        turma = turmaService.save(turma);

        turmaService.delete(turma.getId());

        List<Turma> turmas = turmaService.findAll();

        assertFalse(turmas.contains(turma));
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void administrador_quer_deletar_uma_turma_nao_existente() throws ObjetoNaoEncontrado {
        Turma turma = new Turma();
        turma.setNome("2B");
        turma.setChaveDeAcesso("cebola");

        turma = turmaService.save(turma);

        turmaService.delete(turma.getId());

        turmaService.delete(turma.getId());


    }
}