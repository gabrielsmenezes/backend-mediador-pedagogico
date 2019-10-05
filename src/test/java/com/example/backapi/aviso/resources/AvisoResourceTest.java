package com.example.backapi.aviso.resources;

import com.example.backapi.aviso.domain.AvisoDTO;
import com.example.backapi.aviso.domain.LinkAviso;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
public class AvisoResourceTest {

    AvisoDTO avisoDTO;

    @Autowired
    AvisoResource avisoResource;

    @Before
    public void setUp() throws Exception {
        avisoDTO = new AvisoDTO();
        avisoDTO.setTitulo("Titulo");
        avisoDTO.setDescricao("Descricao");
        avisoDTO.setImagem("Imagem");
        avisoDTO.setLinks(Arrays.asList(new LinkAviso("Link", "nome")));

    }

    @Test
    public void save() throws Exception {
        ResponseEntity<AvisoDTO> retorno = avisoResource.save(avisoDTO);
        assertEquals(201, retorno.getStatusCode().value());
        assertEquals(avisoDTO.getTitulo(), retorno.getBody().getTitulo());
        assertNotNull(retorno.getBody().getId());
    }

    @Test
    public void findById() throws Exception {
        ResponseEntity<AvisoDTO> retorno = avisoResource.save(avisoDTO);
        AvisoDTO avisoEsperado = retorno.getBody();

        AvisoDTO avisoRetornado = avisoResource.findById(avisoEsperado.getId()).getBody();

        assertEquals(avisoEsperado, avisoRetornado);

    }

    @Test
    public void findPage() throws Exception {
        avisoDTO = avisoResource.save(avisoDTO).getBody();
        ResponseEntity<Page<AvisoDTO>> page = avisoResource.findPage(0, 10, "id", "ASC");
        assertTrue(page.getBody().get().anyMatch(aviso -> aviso.equals(avisoDTO)));
    }

    @Test
    public void update() throws Exception {
        avisoDTO = avisoResource.save(avisoDTO).getBody();

        String novoTitulo = "Novo Titulo";
        avisoDTO.setTitulo(novoTitulo);

        avisoDTO = avisoResource.update(avisoDTO.getId(), avisoDTO).getBody();

        assertEquals(novoTitulo, avisoDTO.getTitulo());

    }

    @Test
    public void delete() throws Exception {
        avisoDTO = avisoResource.save(avisoDTO).getBody();
        avisoResource.delete(avisoDTO.getId());
        List<AvisoDTO> avisos = avisoResource.findAll().getBody();
        assertFalse(avisos.contains(avisoDTO));
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < 10; i++) {
            avisoResource.save(avisoDTO);
        }

        List<AvisoDTO> avisos = avisoResource.findAll().getBody();

        assertEquals(10, avisos.size());
    }
}