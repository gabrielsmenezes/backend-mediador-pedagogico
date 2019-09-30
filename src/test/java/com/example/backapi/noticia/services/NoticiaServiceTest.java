package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
    public void administrador_quer_cadastrar_uma_noticia_com_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        assertTrue(noticiaDTO.getId() != null);

    }
    @Test
    public void administrador_quer_cadastrar_uma_noticia_sem_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
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
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_link() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(null);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_notificavel() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(null);

        noticiaService.save(noticiaDTO);

    }

    @Test
    public void aluno_quer_visualizar_noticia() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        Page<Noticia> noticias = noticiaService.findPage(0, 10, "dataDeCriacao", "DESC");

        assertTrue(noticias.getTotalElements() > 0);
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();
        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(false);

        Integer id = noticiaService.save(noticiaDTO).getId();

        noticiaDTO = new NoticiaDTO();
        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(true);
        noticiaDTO.setId(id);

        noticiaService.update(noticiaDTO);

        NoticiaDTO noticiaRetornada = noticiaService.findById(id);

        assertTrue(noticiaRetornada.isNotificavel());
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_titulo(){}

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_descricao(){}

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_link(){}

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_titulo(){}

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outra_descricao(){}

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_link(){}
}