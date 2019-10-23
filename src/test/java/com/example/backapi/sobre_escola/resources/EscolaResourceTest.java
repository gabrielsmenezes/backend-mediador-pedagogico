package com.example.backapi.sobre_escola.resources;

import com.example.backapi.sobre_escola.domain.EscolaDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
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
public class EscolaResourceTest {

    @Autowired
    EscolaResource escolaResource;

    EscolaDTO escolaDTO;
    String nome;
    String imagem;
    String descricao;

    @Before
    public void setUp() throws Exception {
        escolaDTO = new EscolaDTO();
        nome = "Escola Estadual Dona Consuelo Muller";
        imagem = "imagem";
        descricao = "Escola Estadual Dona Consuelo MullerEscola Estadual Dona Consuelo MullerEscola Estadual Dona Consuelo MullerEscola Estadual Dona Consuelo MullerEscola Estadual Dona Consuelo Muller";
        escolaDTO.setNome(nome);
        escolaDTO.setImagem(imagem);
        escolaDTO.setDescricao(descricao);
    }

    @Test
    public void save() throws LimiteDeObjetosAtingido, CampoObrigatorio {
        ResponseEntity<EscolaDTO> resposta = escolaResource.save(escolaDTO);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(nome, resposta.getBody().getNome());
        assertEquals(imagem, resposta.getBody().getImagem());
        assertEquals(descricao, resposta.getBody().getDescricao());
    }

    @Test
    public void update() throws CampoObrigatorio {
        ResponseEntity<EscolaDTO> resposta = escolaResource.update(escolaDTO);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(nome, resposta.getBody().getNome());
        assertEquals(imagem, resposta.getBody().getImagem());
        assertEquals(descricao, resposta.getBody().getDescricao());
    }
}