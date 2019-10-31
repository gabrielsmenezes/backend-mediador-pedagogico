package com.example.backapi.biblioteca.resources;

import com.example.backapi.biblioteca.domain.ItemTopicoDTO;
import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.biblioteca.services.TopicoService;
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

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemResourceTest {

    @Autowired
    ItemResource itemResource;

    @Autowired
    TopicoService topicoService;

    Topico topico;

    ItemTopicoDTO itemTopicoDTO;

    @Before
    public void setUp() throws Exception {
        topico = new Topico();
        topico.setNome("E-books");
        topico = topicoService.save(topico);

        itemTopicoDTO = new ItemTopicoDTO();

        itemTopicoDTO.setIdDoTopico(topico.getId());
        itemTopicoDTO.setNome("O Capital");
        itemTopicoDTO.setLink("https://pt.wikipedia.org/wiki/O_Capital");
    }

    @Test
    public void save() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ResponseEntity<ItemTopicoDTO> retorno = itemResource.save(itemTopicoDTO);

        assertEquals(HttpStatus.CREATED, retorno.getStatusCode());
        assertEquals(itemTopicoDTO.getNome(), retorno.getBody().getNome());
        assertEquals(itemTopicoDTO.getLink(), retorno.getBody().getLink());

    }

    @Test
    public void update() throws CampoObrigatorio, ObjetoNaoEncontrado {
        itemTopicoDTO = itemResource.save(itemTopicoDTO).getBody();

        itemTopicoDTO.setNome("1984");
        itemTopicoDTO.setLink("Link");

        ResponseEntity<ItemTopicoDTO> retorno = itemResource.update(itemTopicoDTO, itemTopicoDTO.getId());

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals("1984", retorno.getBody().getNome());
        assertEquals("Link", retorno.getBody().getLink());
    }

}