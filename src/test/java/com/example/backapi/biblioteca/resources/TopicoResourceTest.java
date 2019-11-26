package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.TopicoDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
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
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TopicoResourceTest {

    @Autowired
    TopicoResource topicoResource;

    TopicoDTO topicoDTO;
    private String nome;

    @Before
    public void setUp() throws Exception {
        topicoDTO = new TopicoDTO();
        nome = "Textos";
        topicoDTO.setNome(nome);
    }

    @Test
    public void save() throws CampoObrigatorio {
        ResponseEntity<TopicoDTO> resposta = topicoResource.save(topicoDTO);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(nome, resposta.getBody().getNome());
    }

    @Test
    public void update() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ResponseEntity<TopicoDTO> resposta = topicoResource.save(topicoDTO);

        topicoDTO = resposta.getBody();

        String novoNome = "novoNome";
        topicoDTO.setNome(novoNome);

        ResponseEntity<TopicoDTO> respostaUpdate = topicoResource.update(topicoDTO.getId(), topicoDTO);

        assertEquals(HttpStatus.OK, respostaUpdate.getStatusCode());
        assertEquals(novoNome, respostaUpdate.getBody().getNome());
    }

    @Test
    public void findAll() throws CampoObrigatorio {
        List<TopicoDTO> topicosEsperados = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ResponseEntity<TopicoDTO> resposta = topicoResource.save(topicoDTO);
            topicosEsperados.add(resposta.getBody());
        }

        List<TopicoDTO> topicosRetornados = topicoResource.findAll().getBody();

        for (TopicoDTO topicoDTO: topicosEsperados) {
            assertTrue(topicosRetornados.contains(topicoDTO));
        }


    }

    @Test
    public void deleteById() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ResponseEntity<TopicoDTO> resposta = topicoResource.save(topicoDTO);
        ResponseEntity<Void> respostaDoDelete = topicoResource.deleteById(resposta.getBody().getId());
        assertEquals(HttpStatus.OK, respostaDoDelete.getStatusCode());
    }

}