package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.turma.TurmaDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TurmaResourceTest {

    @Autowired
    TurmaResource turmaResource;

    @Autowired
    AlunoResource alunoResource;

    TurmaDTO turmaDTO;

    @Before
    public void setUp() {
        turmaDTO = new TurmaDTO();
        turmaDTO.setNome("2C");
        turmaDTO.setChaveDeAcesso("chave");
    }

    @Test
    public void save() throws Exception {
        turmaDTO = turmaResource.save(turmaDTO).getBody();
        assertTrue(turmaDTO.getId() != null);
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            turmaDTO.setNome(String.valueOf(i));
            turmaDTO.setChaveDeAcesso(String.valueOf(i));
            turmaResource.save(turmaDTO).getBody();
        }

        List<TurmaDTO> turmas = turmaResource.findAll().getBody();

        assertEquals(10, turmas.size());

    }

    @Test
    public void delete() throws Exception {
        turmaDTO = turmaResource.save(turmaDTO).getBody();
        turmaResource.delete(turmaDTO.getId());
        List<TurmaDTO> turmas = turmaResource.findAll().getBody();
        assertFalse(turmas.contains(turmaDTO));
    }

    @Test
    public void update() throws Exception {
        turmaDTO = turmaResource.save(turmaDTO).getBody();
        turmaDTO.setNome("NovoNome");
        turmaDTO.setChaveDeAcesso("NovoNome");
        List<TurmaDTO> turmas = turmaResource.findAll().getBody();
        assertTrue(turmas.contains(turmaDTO));
    }

    @Test
    public void findAlunoByIdTurma() throws Exception {
        turmaDTO = turmaResource.save(turmaDTO).getBody();
        Aluno aluno = alunoResource.save("chave", "Gabriel").getBody();
        List<Aluno> alunosEsperados = Arrays.asList(aluno);
        List<Aluno> alunosRetornados = turmaResource.findAlunoByIdTurma(turmaDTO.getId());
        assertEquals(alunosEsperados, alunosRetornados);
    }
}