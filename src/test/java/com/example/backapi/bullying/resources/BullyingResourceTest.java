package com.example.backapi.bullying.resources;

import com.example.backapi.bullying.domain.BullyingDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BullyingResourceTest {

    @Autowired
    BullyingResource bullyingResource;

    BullyingDTO bullyingDTO;

    @Before
    public void setUp() throws Exception {
        bullyingDTO = new BullyingDTO();
        bullyingDTO.setDescricao("descricao");
        bullyingDTO.setImagem("imagem");
        bullyingDTO.setLinkDoFormulario("link");
    }

    @Test
    public void save() throws LimiteDeObjetosAtingido, CampoObrigatorio {
        ResponseEntity<BullyingDTO> retorno = bullyingResource.save(bullyingDTO);
        assertEquals(201, retorno.getStatusCode().value());
        assertEquals(bullyingDTO.getDescricao(), retorno.getBody().getDescricao());
        assertEquals(bullyingDTO.getImagem(), retorno.getBody().getImagem());
        assertEquals(bullyingDTO.getLinkDoFormulario(), retorno.getBody().getLinkDoFormulario());
        assertNotNull(retorno.getBody().getId());

    }

    @Test
    public void update() throws CampoObrigatorio {
        bullyingDTO.setDescricao("NovaDescricao");
        bullyingDTO.setImagem("NovaImagem");
        bullyingDTO.setLinkDoFormulario("NovoLink");

        ResponseEntity<BullyingDTO> retorno = bullyingResource.update(bullyingDTO);
        assertEquals(200, retorno.getStatusCode().value());
        assertEquals(bullyingDTO.getDescricao(), retorno.getBody().getDescricao());
        assertEquals(bullyingDTO.getImagem(), retorno.getBody().getImagem());
        assertEquals(bullyingDTO.getLinkDoFormulario(), retorno.getBody().getLinkDoFormulario());
        assertNotNull(retorno.getBody().getId());
    }

    @Test
    public void find() throws LimiteDeObjetosAtingido, CampoObrigatorio, ObjetoNaoEncontrado {
        bullyingDTO.setDescricao("NovaDescricao");
        bullyingDTO.setImagem("NovaImagem");
        bullyingDTO.setLinkDoFormulario("NovoLink");

        bullyingResource.save(bullyingDTO);
        ResponseEntity<BullyingDTO> retorno = bullyingResource.find();
        assertEquals(200, retorno.getStatusCode().value());
        assertEquals(bullyingDTO.getDescricao(), retorno.getBody().getDescricao());
        assertEquals(bullyingDTO.getImagem(), retorno.getBody().getImagem());
        assertEquals(bullyingDTO.getLinkDoFormulario(), retorno.getBody().getLinkDoFormulario());
        assertNotNull(retorno.getBody().getId());
    }
}