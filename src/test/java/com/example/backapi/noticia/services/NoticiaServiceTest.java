package com.example.backapi.noticia.services;

import com.example.backapi.noticia.domain.NoticiaDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import com.example.backapi.utils.exceptions.TamanhoDeCampoExcedente;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

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
    public void setUp() {
        this.titulo = "Título";
        this.descricao = "Descrição";
        this.link = "hhtps://www.link.com";
        this.notificavel =  true;
    }

    @Test
    public void administrador_quer_cadastrar_uma_noticia_com_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
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
    public void administrador_quer_cadastrar_uma_noticia_sem_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
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
    public void administrador_quer_cadastrar_uma_noticia_sem_titulo2() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo("");
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = TamanhoDeCampoExcedente.class)
    public void administrador_quer_cadastrar_uma_noticia_com_titulo_maior_que_50_caracteres() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo("O amor, quando se revela,\n" +
                "Não se sabe revelar.\n" +
                "Sabe bem olhar p'ra ela,\n" +
                "Mas não lhe sabe falar.\n" +
                "\n" +
                "Quem quer dizer o que sente\n" +
                "Não sabe o que há de dizer.\n" +
                "Fala: parece que mente...\n" +
                "Cala: parece esquecer...\n" +
                "\n" +
                "Ah, mas se ela adivinhasse,\n" +
                "Se pudesse ouvir o olhar,\n" +
                "E se um olhar lhe bastasse\n" +
                "P'ra saber que a estão a amar!\n" +
                "\n" +
                "Mas quem sente muito, cala;\n" +
                "Quem quer dizer quanto sente\n" +
                "Fica sem alma nem fala,\n" +
                "Fica só, inteiramente!\n" +
                "\n" +
                "Mas se isto puder contar-lhe\n" +
                "O que não lhe ouso contar,\n" +
                "Já não terei que falar-lhe\n" +
                "Porque lhe estou a falar...");
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test(expected = TamanhoDeCampoExcedente.class)
    public void administrador_quer_cadastrar_uma_noticia_com_descricao_maior_que_100_caracteres() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setDescricao("O amor, quando se revela,\n" +
                "Não se sabe revelar.\n" +
                "Sabe bem olhar p'ra ela,\n" +
                "Mas não lhe sabe falar.\n" +
                "\n" +
                "Quem quer dizer o que sente\n" +
                "Não sabe o que há de dizer.\n" +
                "Fala: parece que mente...\n" +
                "Cala: parece esquecer...\n" +
                "\n" +
                "Ah, mas se ela adivinhasse,\n" +
                "Se pudesse ouvir o olhar,\n" +
                "E se um olhar lhe bastasse\n" +
                "P'ra saber que a estão a amar!\n" +
                "\n" +
                "Mas quem sente muito, cala;\n" +
                "Quem quer dizer quanto sente\n" +
                "Fica sem alma nem fala,\n" +
                "Fica só, inteiramente!\n" +
                "\n" +
                "Mas se isto puder contar-lhe\n" +
                "O que não lhe ouso contar,\n" +
                "Já não terei que falar-lhe\n" +
                "Porque lhe estou a falar...");
        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);
    }

    @Test
    public void administrador_quer_cadastrar_uma_noticia_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        NoticiaDTO noticiaRetornada = noticiaService.findById(noticiaDTO.getId());

        assertTrue(noticiaRetornada.getDescricao() == null);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_link() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(null);
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_link2() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks("");
        noticiaDTO.setNotificavel(notificavel);

        noticiaService.save(noticiaDTO);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_cadastrar_uma_noticia_sem_notificavel() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(null);

        noticiaService.save(noticiaDTO);

    }

    @Test
    public void aluno_quer_visualizar_noticia() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        NoticiaDTO noticiaDTO = new NoticiaDTO();

        notificavel = false;

        noticiaDTO.setTitulo(titulo);
        noticiaDTO.setDescricao(descricao);
        noticiaDTO.setLinks(link);
        noticiaDTO.setNotificavel(notificavel);

        noticiaDTO = noticiaService.save(noticiaDTO);

        Page<NoticiaDTO> noticias = noticiaService.findPage(0, 10, "dataDeCriacao", "DESC");

        assertTrue(noticias.getTotalElements() > 0);
    }

    //Testes do cenario da US-31 Editar noticias Web

    @Test
    public void administrador_quer_editar_uma_noticia_para_que_tenha_notificacao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_titulo() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_fique_sem_link() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_titulo() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outra_descricao() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_editar_uma_noticia_para_que_tenha_outro_link() throws TamanhoDeCampoExcedente, CampoObrigatorio, ObjetoNaoEncontrado, IOException, FirebaseMessagingException {
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
    public void administrador_quer_deletar_uma_noticia() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
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

    //Testes do cenario da US-33 Visualizar listagem de notícias - Web
    @Test
    public void administrador_quer_listar_todas_as_noticias_cadastradas() throws TamanhoDeCampoExcedente, CampoObrigatorio, IOException, FirebaseMessagingException {
        for (int i = 0;i < 12; i++) {
            NoticiaDTO noticiaDTO = new NoticiaDTO();

            noticiaDTO.setTitulo(String.valueOf(i));
            noticiaDTO.setDescricao(String.valueOf(i));
            noticiaDTO.setLinks(String.valueOf(i));
            noticiaDTO.setNotificavel(false);

            noticiaService.save(noticiaDTO);
        }

        assertEquals(12, noticiaService.findAll().size());

        }
    }