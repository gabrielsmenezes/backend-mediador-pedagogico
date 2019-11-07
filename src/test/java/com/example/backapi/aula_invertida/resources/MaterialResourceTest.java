package com.example.backapi.aula_invertida.resources;

import com.example.backapi.aula_invertida.domain.aluno.Aluno;
import com.example.backapi.aula_invertida.domain.material.LinkMaterial;
import com.example.backapi.aula_invertida.domain.material.MaterialDTO;
import com.example.backapi.aula_invertida.domain.turma.TurmaDTO;
import com.example.backapi.utils.exceptions.AcessoNegado;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class MaterialResourceTest {

    @Autowired
    MaterialResource materialResource;

    @Autowired
    TurmaResource turmaResource;

    @Autowired
    AlunoResource alunoResource;

    private MaterialDTO material;
    private TurmaDTO turmaDTO;
    private Aluno aluno;

    @Before
    public void setUp() throws Exception {
        material = new MaterialDTO();
        material.setTitulo("Titulo");
        material.setDescricao("Descricao");
        material.setImagem("Imagem");
        material.setLinks(Arrays.asList(new LinkMaterial("Link", "Nome")));
        turmaDTO = new TurmaDTO();
        turmaDTO.setChaveDeAcesso("chave");
        turmaDTO.setNome("2C");
        turmaDTO = turmaResource.save(turmaDTO).getBody();
        material.setTurmaId(turmaDTO.getId());
        aluno = alunoResource.save(turmaDTO.getChaveDeAcesso(), "Sam Winchester").getBody();
    }

    @Test
    public void save() throws Exception {
        MaterialDTO material_esperado = materialResource.save(material).getBody();
        assertNotNull(material_esperado.getId());
    }

    @Test
    public void findPage() throws FirebaseMessagingException, ObjetoNaoEncontrado, CampoObrigatorio, IOException, AcessoNegado {
        List<MaterialDTO> esperados = new ArrayList<MaterialDTO>();

        for (int i = 0; i < 10; i++) {
            MaterialDTO material_esperado = materialResource.save(material).getBody();
            esperados.add(material_esperado);
        }

        List<MaterialDTO> retornados = materialResource.findPage(0, 10, "id", "ASC", turmaDTO.getChaveDeAcesso(), aluno.getId()).getBody().getContent();

        assertEquals(esperados, retornados);
    }

    @Test
    public void update() throws FirebaseMessagingException, ObjetoNaoEncontrado, CampoObrigatorio, IOException {
        MaterialDTO material_esperado = materialResource.save(material).getBody();
        material_esperado.setTitulo("NovoTitulo");
        material_esperado.setDescricao("NovaDescricao");

        materialResource.update(material_esperado.getId(), material_esperado);

        List<MaterialDTO> materiais = materialResource.findAll(material_esperado.getTurmaId()).getBody();

        MaterialDTO material_retornado = materiais.get(materiais.indexOf(material_esperado));

        assertEquals(material_esperado.getTitulo(), material_retornado.getTitulo());
        assertEquals(material_esperado.getDescricao(), material_retornado.getDescricao());
    }

    @Test
    public void findAll() throws FirebaseMessagingException, ObjetoNaoEncontrado, CampoObrigatorio, IOException {
        List<MaterialDTO> esperados = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MaterialDTO material_esperado = materialResource.save(material).getBody();
            esperados.add(material_esperado);
        }

        List<MaterialDTO> retornados = materialResource.findAll(turmaDTO.getId()).getBody();

        assertEquals(esperados, retornados);
    }

    @Test
    public void delete() throws FirebaseMessagingException, ObjetoNaoEncontrado, CampoObrigatorio, IOException {
        MaterialDTO material_esperado = materialResource.save(material).getBody();
        materialResource.delete(material_esperado.getId());
        List<MaterialDTO> materiais = materialResource.findAll(turmaDTO.getId()).getBody();
        assertFalse(materiais.contains(material_esperado));
    }
}