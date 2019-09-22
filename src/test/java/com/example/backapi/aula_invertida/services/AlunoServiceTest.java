package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.Turma;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AlunoServiceTest {

    private Turma turma;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private AlunoService alunoService;

    @Before
    public void setUp() throws Exception {
        turma = new Turma();
        turma.setNome("2B");
        turma.setChaveDeAcesso("mat2");
        turmaService.save(turma);
    }

    @Test
    public void aluno_insere_a_chave_da_turma_e_insere_seu_nome_completo() throws ObjetoNaoEncontrado, CampoObrigatorio {
        Aluno alunoEsperado = new Aluno();
        alunoEsperado.setNome("Gabriel Menezes");
        alunoEsperado.setChaveDeAcesso("mat2");

        Aluno alunoRetornado = alunoService.save(alunoEsperado);

        assertEquals(alunoEsperado, alunoRetornado);
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void aluno_insere_a_chave_da_turma_incorreto() throws ObjetoNaoEncontrado, CampoObrigatorio {
        Aluno aluno = new Aluno();
        aluno.setNome("Gabriel Menezes");
        aluno.setChaveDeAcesso("3A");

        alunoService.save(aluno);
    }

    @Test(expected = CampoObrigatorio.class)
    public void aluno_nao_insere_seu_nome() throws ObjetoNaoEncontrado, CampoObrigatorio {
        Aluno aluno = new Aluno();
        aluno.setChaveDeAcesso("mat2");

        alunoService.save(aluno);

    }

    @Test(expected = CampoObrigatorio.class)
    public void aluno_nao_insere_a_chave_da_turma() throws ObjetoNaoEncontrado, CampoObrigatorio {
        Aluno aluno = new Aluno();
        aluno.setNome("Gabriel Menezes");

        alunoService.save(aluno);

    }

}