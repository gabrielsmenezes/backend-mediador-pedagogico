package com.example.backapi.sobre_escola.resources;

import com.example.backapi.sobre_escola.domain.ProfessorDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProfessorResourceTest {

    @Autowired
    ProfessorResource professorResource;

    ProfessorDTO professor;
    String nome;
    List<String> disciplinas = new ArrayList<>();
    String descricao;

    @Before
    public void setUp() throws Exception {
        professor = new ProfessorDTO();
        nome = "Bruno Cafeo";
        descricao = "Professor Adjunto-A da Universidade Federal de Mato Grosso do Sul. É Doutor em Informática pela Pontifícia Universidade Católica do Rio de Janeiro (PUC-RIO), Bacharel em Ciências de Computação e Mestre em Ciências de Computação e Matemática Computacional pelo Instituto de Ciências Matemáticas e de Computação (ICMC) da Universidade de São Paulo (USP), campus de São Carlos. É também Técnico em Informática pelo Colégio Técnico Industrial (CTI) &quot;Prof. Isaac Portal Róldan&quot; da Universidade Estadual Paulista (UNESP), campus de Bauru.";
        disciplinas = Arrays.asList("Verificação, Validação e Teste II", "Manutenção");
        professor.setNome(nome);
        professor.setDisciplinas(disciplinas);
        professor.setDescricao(descricao);
    }
    @Test
    public void save() {
        ResponseEntity<ProfessorDTO> retorno = professorResource.save(professor);

        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
        assertEquals(nome, retorno.getBody().getNome());
        assertEquals(disciplinas, retorno.getBody().getDisciplinas());
        assertEquals(descricao, retorno.getBody().getDescricao());
    }

    @Test
    public void update() {
        professorResource.save(professor);
        nome = "Cruno Bafeo";
        descricao = "É Doutor em Católica do Rio de Janeiro (PUC-RIO), Bacharel em Ciências de Computação e Mestre em Ciências de Computação e Matemática Computacional pelo Instituto de Ciências Matemáticas e de Computação (ICMC) da Universidade de São Paulo (USP), campus de São Carlos. É também Técnico em Informática pelo Colégio Técnico Industrial (CTI) &quot;Prof. Isaac Portal Róldan&quot; da Universidade Estadual Paulista (UNESP), campus de Bauru.";
        disciplinas = Arrays.asList(" e Teste II", "Manutenção");
        professor.setNome(nome);
        professor.setDisciplinas(disciplinas);
        professor.setDescricao(descricao);

        ResponseEntity<ProfessorDTO> retorno = professorResource.update(professor);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(nome, retorno.getBody().getNome());
        assertEquals(disciplinas, retorno.getBody().getDisciplinas());
        assertEquals(descricao, retorno.getBody().getDescricao());

    }

    @Test
    public void delete() {
        ResponseEntity<ProfessorDTO> retorno = professorResource.save(professor);
        ResponseEntity<Void> retornoVoid = professorResource.delete(retorno.getBody().getId());
        List<ProfessorDTO> professores = professorResource.findAll().getBody();
        assertFalse(professores.contains(retorno.getBody()));
    }

    @Test
    public void findAll() {
        professor.setId(null);
        professorResource.save(professor);
        professor.setId(null);
        professorResource.save(professor);
        professor.setId(null);
        professorResource.save(professor);
        professor.setId(null);
        professorResource.save(professor);

        List<ProfessorDTO> professores = professorResource.findAll().getBody();

        assertEquals(4, professores.size());
    }
}