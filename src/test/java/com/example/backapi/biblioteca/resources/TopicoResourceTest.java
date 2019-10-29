package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.TopicoDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.google.api.Http;
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
}