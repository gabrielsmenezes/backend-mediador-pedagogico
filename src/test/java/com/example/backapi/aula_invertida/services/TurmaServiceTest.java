package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Turma;
import com.example.backapi.aula_invertida.services.TurmaService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TurmaServiceTest {

    @Autowired
    TurmaService turmaService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void administrador_criar_uma_turma_com_nome_da_turma_e_chave_de_acesso() throws FirebaseMessagingException {
        String nomeDaTurma = "3° B";
        String chaveDeAcesso = "terceiroB";

        Turma turmaEsperada = new Turma();
        turmaEsperada.setNome(nomeDaTurma);
        turmaEsperada.setChaveDeAcesso(chaveDeAcesso);

        Turma turmaRetornada = turmaService.save(turmaEsperada);

        assertEquals(turmaEsperada, turmaRetornada);

    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_sem_nome() throws FirebaseMessagingException {
        Turma turma = new Turma(null, "segundoA", null, null);

        turmaService.save(turma);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_turma_sem_chave_de_acesso_da_turma() throws FirebaseMessagingException {
        Turma turma;
        turma = new Turma("2°A", null, null, null);

        turmaService.save(turma);

    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_o_nome_ja_existente() throws FirebaseMessagingException {
        turmaService.save(new Turma("2A", "abobora", null, null));

        turmaService.save(new Turma("2A", "abacaxi", null, null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_chave_de_acesso_existente() throws FirebaseMessagingException {

        turmaService.save(new Turma("2C", "abacaxi", null, null));

        turmaService.save(new Turma("2A", "abacaxi", null, null));

    }

}