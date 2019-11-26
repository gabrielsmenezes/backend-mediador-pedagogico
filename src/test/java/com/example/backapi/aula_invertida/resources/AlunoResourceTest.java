package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.TurmaDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AlunoResourceTest {

    @Autowired
    AlunoResource alunoResource;

    @Autowired
    TurmaResource turmaResource;

    private TurmaDTO turma;
    private String nome;

    @Before
    public void setUp() throws Exception {
        nome = "Gabriel";
        String chave = "chave";

        turma = new TurmaDTO();
        turma.setNome("2C");
        turma.setChaveDeAcesso(chave);

        turmaResource.save(turma);
    }

    @Test
    public void save() throws Exception {
        Aluno aluno = alunoResource.save(turma.getChaveDeAcesso(), nome).getBody();
        assertTrue(aluno.getId()!=null);
    }

    @Test
    public void delete() throws Exception {
        Aluno aluno = alunoResource.save(turma.getChaveDeAcesso(), nome).getBody();
        ResponseEntity<Void> retorno = alunoResource.delete(aluno.getId());
        assertEquals(200, retorno.getStatusCode().value());
    }
}