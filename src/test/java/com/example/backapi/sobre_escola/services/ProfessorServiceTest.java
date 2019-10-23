package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Professor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProfessorServiceTest {

    @Autowired
    ProfessorService professorService;

    Professor professor;

    String nome;
    List<String> disciplinas = new ArrayList<>();
    String descricao;

    @Before
    public void setUp() throws Exception {
        professor = new Professor();
        nome = "Bruno Cafeo";
        descricao = "Professor Adjunto-A da Universidade Federal de Mato Grosso do Sul. É Doutor em Informática pela Pontifícia Universidade Católica do Rio de Janeiro (PUC-RIO), Bacharel em Ciências de Computação e Mestre em Ciências de Computação e Matemática Computacional pelo Instituto de Ciências Matemáticas e de Computação (ICMC) da Universidade de São Paulo (USP), campus de São Carlos. É também Técnico em Informática pelo Colégio Técnico Industrial (CTI) &quot;Prof. Isaac Portal Róldan&quot; da Universidade Estadual Paulista (UNESP), campus de Bauru.";
        disciplinas = Arrays.asList("Verificação, Validação e Teste II", "Manutenção");
        professor.setNome(nome);
        professor.setDisciplinas(disciplinas);
        professor.setDescricao(descricao);
    }

    @Test
    public void save() {
        professor = professorService.save(professor);
        assertNotNull(professor.getId());
    }

    @Test
    public void update() {
        professor = professorService.save(professor);
        String novoNome = "novoNome";
        professor.setNome(novoNome);
        String novoDescricao = "NovoDescricao";
        professor.setDescricao(novoDescricao);
        professor.setDisciplinas(null);

        assertEquals(novoNome, professor.getNome());
        assertEquals(novoDescricao, professor.getDescricao());
        assertNull(professor.getDisciplinas());
    }

    @Test
    public void delete() {
        professor = professorService.save(professor);
        professorService.delete(professor.getId());
        assertFalse(professorService.findAll().contains(professor));
    }

    @Test
    public void findAll() {
        professor = professorService.save(professor);
        professor.setId(null);
        professor = professorService.save(professor);
        professor.setId(null);
        professor = professorService.save(professor);
        professor.setId(null);
        professor = professorService.save(professor);

        assertTrue(professorService.findAll().contains(professor));

    }
}