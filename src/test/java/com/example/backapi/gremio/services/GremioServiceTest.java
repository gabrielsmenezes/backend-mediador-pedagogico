package com.example.backapi.gremio.services;

import com.example.backapi.gremio.domain.Gremio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GremioServiceTest {

    @Autowired
    GremioService gremioService;

    private Gremio gremio;
    private String link;
    private String imagem;
    private String descricao;


    @Before
    public void setUp() throws Exception {
        gremio = new Gremio();
        link = "www.google.com";
        imagem = "imagem";
        descricao = "descricao";
    }

    //Cadastrar Grêmio - Web

    @Test
    public void administrador_quer_cadastrar_as_informações_do_gremio_com_sucesso() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);
        assertNotNull(gremio.getId());
    }

    @Test
    public void administrador_quer_inserir_as_informações_sem_link() throws LimiteDeObjetosAtingido {
        gremio.setLink(null);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);
        assertNotNull(gremio.getId());
    }

    @Test
    public void administrador_quer_inserir_as_informações_do_grêmio_sem_imagem() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(null);
        gremio = gremioService.save(gremio);
        assertNotNull(gremio.getId());
    }

    @Test
    public void administrador_quer_inserir_as_informações_do_gremio_sem_descricao() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(null);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);
        assertNotNull(gremio.getId());
    }

    @Test(expected = LimiteDeObjetosAtingido.class)
    public void administrador_quer_cadastrar_mais_de_um_gremio() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);
        gremio.setId(null);
        gremio = gremioService.save(gremio);
    }

    //Editar Grêmio - Web

    @Test
    public void administrador_quer_editar_as_informações_do_gremio_com_sucesso() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);

        String nova_descricao = "Nova descricao";
        gremio.setDescricao(nova_descricao);
        String novo_link = "Novo link";
        gremio.setLink(novo_link);
        String nova_imagem = "Nova Imagem";
        gremio.setImagem(nova_imagem);
        gremio = gremioService.update(gremio);

        assertEquals(nova_descricao, gremio.getDescricao());
        assertEquals(novo_link, gremio.getLink());
        assertEquals(nova_imagem, gremio.getImagem());
    }

    @Test
    public void administrador_quer_editar_as_informações_sem_link() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);

        gremio.setLink(null);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.update(gremio);

        assertNotNull(gremio.getId());
    }

    @Test
    public void administrador_quer_editar_as_informações_do_grêmio_sem_imagem() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);

        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(null);

        gremio = gremioService.update(gremio);
        assertNotNull(gremio.getId());
    }

    @Test
    public void administrador_quer_editar_as_informações_do_gremio_sem_descricao() throws LimiteDeObjetosAtingido {
        gremio.setLink(link);
        gremio.setDescricao(descricao);
        gremio.setImagem(imagem);
        gremio = gremioService.save(gremio);

        gremio.setLink(link);
        gremio.setDescricao(null);
        gremio.setImagem(imagem);
        gremio = gremioService.update(gremio);
        assertNotNull(gremio.getId());
    }

}