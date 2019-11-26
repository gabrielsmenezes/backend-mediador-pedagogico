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

    @Autowired
    AlunoService alunoService;

    String nomeDaTurma;
    String chaveDeAcesso;
    Turma turma;

    @Before
    public void setUp() {
        turma = new Turma();
        nomeDaTurma = "3° B";
        chaveDeAcesso = "terceiroB";
        turma.setNome(nomeDaTurma);
        turma.setChaveDeAcesso(chaveDeAcesso);
    }

    @Test
    public void administrador_criar_uma_turma_com_nome_da_turma_e_chave_de_acesso() throws CampoObrigatorio {
        Turma turmaRetornada = turmaService.save(turma);
        assertEquals(turma, turmaRetornada);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_criar_uma_turma_sem_nome() throws CampoObrigatorio {
        turma.setNome(null);
        turmaService.save(turma);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_criar_uma_turma_sem_nome2() throws CampoObrigatorio {
        turma.setNome("");
        turmaService.save(turma);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_criar_turma_sem_chave_de_acesso_da_turma() throws CampoObrigatorio {
        turma.setChaveDeAcesso(null);
        turmaService.save(turma);

    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_o_nome_ja_existente() throws CampoObrigatorio {
        turma.setChaveDeAcesso("a");
        turmaService.save(turma);
        turma.setId(null);
        turma.setChaveDeAcesso("b");
        turmaService.save(turma);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_criar_uma_turma_com_chave_de_acesso_existente() throws CampoObrigatorio {
        turma.setNome("a");
        turmaService.save(turma);
        turma.setId(null);
        turma.setNome("b");
        turmaService.save(turma);
    }

    @Test
    public void administrador_lista_as_turmas_com_sucesso() throws CampoObrigatorio {
        turma = turmaService.save(turma);

        List<Turma> turmas = turmaService.findAll();

        assertTrue(turmas.contains(turma));
    }

    @Test
    public void administrador_quer_deletar_uma_turma() throws ObjetoNaoEncontrado, CampoObrigatorio {
        turma = turmaService.save(turma);

        turmaService.delete(turma.getId());

        List<Turma> turmas = turmaService.findAll();

        assertFalse(turmas.contains(turma));
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void administrador_quer_deletar_uma_turma_nao_existente() throws ObjetoNaoEncontrado, CampoObrigatorio {
        Turma turma = turmaService.save(this.turma);

        turmaService.delete(turma.getId());

        turmaService.delete(turma.getId());


    }

    //Testes do US-23 Editar Turma - Web

    @Test
    public void administrador_quer_editar_turma_com_sucesso() throws CampoObrigatorio, ObjetoNaoEncontrado {
        turma = turmaService.save(turma);

        String novaChaveDeAcesso = "nao cebola";
        String novoNome = "2A";

        Turma turmaEditada = new Turma();
        turmaEditada.setNome(novoNome);
        turmaEditada.setChaveDeAcesso(novaChaveDeAcesso);
        turmaEditada.setId(turma.getId());

        turmaEditada = turmaService.update(turmaEditada);

        assertEquals(novaChaveDeAcesso, turmaEditada.getChaveDeAcesso());
        assertEquals(novoNome, turmaEditada.getNome());
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_uma_turma_sem_nome() throws CampoObrigatorio, ObjetoNaoEncontrado {


        turma = turmaService.save(turma);

        String novaChaveDeAcesso = "nao cebola";

        Turma turmaEditada = new Turma();
        turmaEditada.setNome(null);
        turmaEditada.setChaveDeAcesso(novaChaveDeAcesso);
        turmaEditada.setId(turma.getId());

        turmaService.update(turmaEditada);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_turma_sem_chave_de_acesso_da_turma() throws CampoObrigatorio, ObjetoNaoEncontrado {
        turma = turmaService.save(turma);

        String novoNome = "2A";

        Turma turmaEditada = new Turma();
        turmaEditada.setNome(novoNome);
        turmaEditada.setChaveDeAcesso(null);
        turmaEditada.setId(turma.getId());


        turmaService.update(turmaEditada);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_editar_uma_turma_com_o_nome_ja_existente() throws CampoObrigatorio, ObjetoNaoEncontrado {
        turmaService.save(turma);

        Turma turma2 = new Turma();
        turma2.setNome("2C");
        turma2.setChaveDeAcesso("abacate");

        turmaService.save(turma2);

        Turma turmaEditada = new Turma();
        turmaEditada.setId(turma2.getId());
        turmaEditada.setNome("2B");
        turmaEditada.setChaveDeAcesso("abacate");

        turmaService.update(turmaEditada);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_editar_uma_turma_com_chave_de_acesso_existente() throws CampoObrigatorio, ObjetoNaoEncontrado {
        turmaService.save(turma);

        Turma turma2 = new Turma();
        turma2.setNome("2C");
        turma2.setChaveDeAcesso("abacate");

        turmaService.save(turma2);

        Turma turmaEditada = new Turma();
        turmaEditada.setId(turma2.getId());
        turmaEditada.setNome("2E");
        turmaEditada.setChaveDeAcesso("terceiroB");

        turmaService.update(turmaEditada);
    }

    @Test(expected = ConstraintViolationException.class)
    public void administrador_quer_editar_a_turma_com_a_chave_de_acesso_ja_usada_recentemente() throws CampoObrigatorio, ObjetoNaoEncontrado {
        turma = turmaService.save(turma);

        String novaChaveDeAcesso = "terceiroB";

        Turma turmaEditada = new Turma();
        turmaEditada.setNome("2B");
        turmaEditada.setChaveDeAcesso(novaChaveDeAcesso);
        turmaEditada.setId(turma.getId());

        turmaService.update(turmaEditada);


    }

    //Testes do US-26 Listar alunos referente à uma turma - Web

    @Test
    public void administrador_quer_listar_todos_os_alunos_de_uma_turma() throws CampoObrigatorio, ObjetoNaoEncontrado {

        Turma turmaRetornada = turmaService.save(turma);

        Aluno gabriel = new Aluno();
        gabriel.setNome("Gabriel");
        gabriel.setChaveDeAcesso(chaveDeAcesso);

        alunoService.save(gabriel);

        List<Aluno> alunos = turmaService.findAlunoByTurmaId(turmaRetornada.getId());

        assertTrue(alunos.contains(gabriel));
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void administrador_quer_listar_alunos_de_uma_turma_inexistente() throws ObjetoNaoEncontrado {
        turmaService.findAlunoByTurmaId(0);
    }

    @Test
    public void administrador_quer_deletar_todas_turmas() throws CampoObrigatorio {
        turmaService.save(turma);
        turmaService.deleteAll();
        assertTrue(turmaService.findAll().isEmpty());
    }
}