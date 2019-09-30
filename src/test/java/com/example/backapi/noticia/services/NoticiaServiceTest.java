package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.junit.Before;
import org.junit.Ignore;
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
public class NoticiaServiceTest {

    private String titulo;
    private String descricao;
    private String link;
    private boolean notificavel;

    @Autowired
    NoticiaService noticiaService;

    @Before
    public void setUp() throws Exception {
        this.titulo = "Título";
        this.descricao = "Descrição";
        this.link = "hhtps://www.link.com";
        this.notificavel =  true;
    }

    @Test
    public void administrador_quer_cadastrar_uma_notícia_com_notificação() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLink(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        assertTrue(noticiaDTO.getId() != null);

    }
    @Test
    public void administrador_quer_cadastrar_uma_notícia_sem_notificação() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLink(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        assertTrue(noticiaDTO.getId() != null);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_titulo() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(null);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLink(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setLink(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_link() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLink(null);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_notificavel() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLink(link);
        noticiaDTO.setNotificavel(null);

        noticiaService.save(noticiaDTO);

    }

}