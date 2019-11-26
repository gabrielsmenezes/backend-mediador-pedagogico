package com.example.backapi.noticia.resources;

import com.example.backapi.noticia.domain.NoticiaDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
public class NoticiaResourceTest {

    @Autowired
    NoticiaResource noticiaResource;

    NoticiaDTO noticiaDTO;

    @Before
    public void setUp() {
        noticiaDTO = new NoticiaDTO();
        noticiaDTO.setTitulo("Titulo");
        noticiaDTO.setDescricao("Descricao");
        noticiaDTO.setLinks("www.google.com");
        noticiaDTO.setNotificavel(false);
    }

    @Test
    public void save() throws Exception {
        noticiaDTO = noticiaResource.save(noticiaDTO).getBody();
        assertTrue(noticiaDTO.getId() != null);
    }

    @Test
    public void findPage() throws Exception {
        List<NoticiaDTO> noticiasEsperado = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            noticiasEsperado.add(noticiaResource.save(noticiaDTO).getBody());
        }

        Page<NoticiaDTO> noticiasRetornado = noticiaResource.findPage(0, 10, "id", "DESC").getBody();

        assertEquals(noticiasEsperado.size(), noticiasRetornado.getTotalElements());
    }

    @Test
    public void update() throws Exception {
        noticiaDTO = noticiaResource.save(noticiaDTO).getBody();

        noticiaDTO.setTitulo("Novo Titulo");

        noticiaDTO = noticiaResource.update(noticiaDTO.getId(), noticiaDTO).getBody();

        assertTrue(noticiaDTO.getTitulo().equals("Novo Titulo"));
    }

    @Test
    public void delete() throws Exception {
        noticiaDTO = noticiaResource.save(noticiaDTO).getBody();
        noticiaResource.delete(noticiaDTO.getId());
        List<NoticiaDTO> noticias = noticiaResource.findAll().getBody();
        assertFalse(noticias.contains(noticiaDTO));
    }

    @Test
    public void findAll() throws Exception {
        ArrayList<NoticiaDTO> noticias = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            noticias.add(noticiaResource.save(noticiaDTO).getBody());
        }

        List<NoticiaDTO> noticiasRetornadas = noticiaResource.findAll().getBody();

        assertEquals(noticias.size(), noticiasRetornadas.size());
    }
}