package com.example.backapi.gremio.resources;

import com.example.backapi.gremio.domain.GremioDTO;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
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
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GremioResourceTest {

    @Autowired
    GremioResource gremioResource;

    GremioDTO gremioDTO;
    String descricao;
    String imagem;
    String link;

    @Before
    public void setUp() throws Exception {
        gremioDTO = new GremioDTO();
        descricao = "descricao";
        imagem = "imagem";
        link = "link";
    }

    @Test
    public void save() throws LimiteDeObjetosAtingido {
        gremioDTO.setDescricao(descricao);
        gremioDTO.setImagem(imagem);
        gremioDTO.setLink(link);

        ResponseEntity<GremioDTO> resposta = gremioResource.save(gremioDTO);
        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals(descricao, resposta.getBody().getDescricao());
        assertEquals(imagem, resposta.getBody().getImagem());
        assertEquals(link, resposta.getBody().getLink());
    }
}