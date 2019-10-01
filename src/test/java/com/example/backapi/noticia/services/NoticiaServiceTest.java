package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.Noticia;
import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import org.junit.Before;
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

    @Test
    public void administrador_quer_cadastrar_uma_noticia_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaRetornada = noticiaService.findById(noticiaDTO.getId());

        assertTrue(noticiaDTO.getDescricao() == null);
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

    //Testes do cenario da US-31 Editar noticias Web

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

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_titulo() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        noticiaEditada.setTitulo(null);
        noticiaEditada.setDescricao(descricao);
        noticiaEditada.setLinks(link);
        noticiaEditada.setNotificavel(notificavel);

        noticiaService.update(noticiaEditada);
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        noticiaEditada.setTitulo(titulo);
        noticiaEditada.setDescricao(null);
        noticiaEditada.setLinks(link);
        noticiaEditada.setNotificavel(notificavel);

        noticiaEditada = noticiaService.update(noticiaEditada);

        assertTrue(noticiaEditada.getDescricao() == null);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_link() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        noticiaEditada.setTitulo(titulo);
        noticiaEditada.setDescricao(descricao);
        noticiaEditada.setLinks(null);
        noticiaEditada.setNotificavel(notificavel);

        noticiaService.update(noticiaEditada);
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_titulo() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        String novoTitulo = "Alterei o titulo";
        noticiaEditada.setTitulo(novoTitulo);
        noticiaEditada.setDescricao(descricao);
        noticiaEditada.setLinks(link);
        noticiaEditada.setNotificavel(notificavel);

        noticiaEditada = noticiaService.update(noticiaEditada);

        assertEquals(novoTitulo, noticiaEditada.getTitulo());
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outra_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        String novoTitulo = "Alterei o titulo";
        noticiaEditada.setTitulo(novoTitulo);
        noticiaEditada.setDescricao(descricao);
        noticiaEditada.setLinks(link);
        noticiaEditada.setNotificavel(notificavel);

        noticiaEditada = noticiaService.update(noticiaEditada);

        assertEquals(novoTitulo, noticiaEditada.getTitulo());
    }

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_link() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaEditada = new NoticiaDTO();
        noticiaEditada.setId(noticiaDTO.getId());
        String novoLink = "www.novosite.com";
        noticiaEditada.setTitulo(titulo);
        noticiaEditada.setDescricao(descricao);
        noticiaEditada.setLinks(novoLink);
        noticiaEditada.setNotificavel(notificavel);

        noticiaEditada = noticiaService.update(noticiaEditada);

        assertEquals(novoLink, noticiaEditada.getLinks());
    }

    //Testes do cenario da US-32 Deletar noticias Web

    @Test
    public void administrador_quer_deletar_uma_noticia() throws TamanhoDeCampoExcedente, CampoObrigatorio {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        noticiaService.delete(noticiaDTO.getId());

        assertFalse(noticiaService.findAll().contains(noticiaDTO));

    }
}