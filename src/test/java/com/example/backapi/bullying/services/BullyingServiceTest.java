package com.example.backapi.bullying.services;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
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
public class BullyingServiceTest {

    @Autowired
    BullyingService bullyingService;

    private String descricao;
    private String imagem;
    private String linkDoFormulario;
    private Bullying bullyingEsperado;

    @Before
    public void setUp() throws Exception {
        bullyingEsperado = new Bullying();
        descricao = "Bullying é a prática de atos violentos, intencionais e repetidos, contra uma pessoa indefesa, que podem causar danos físicos e psicológicos às vítimas. O termo surgiu a partir do inglês bully, palavra que significa tirano, brigão ou valentão, na tradução para o português";
        imagem = "imagem";
        linkDoFormulario = "www.google.com";
    }

    // Testes do Cadastrar Bullying

    @Test
    public void administrador_quer_cadastrar_as_informações_de_bullying_com_sucesso() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        assertEquals(bullyingEsperado, bullyingRetornado);

    }

    @Test(expected = CampoObrigatorio.class)
    public void adminstrador_quer_inserir_as_informações_sem_link() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);

        bullyingService.save(bullyingEsperado);
    }

    @Test
    public void administrador_quer_inserir_as_informações_de_bullying_sem_imagem() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);
        bullyingEsperado.setDescricao(descricao);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        assertNotNull(bullyingRetornado.getId());
    }

    @Test
    public void administrador_quer_inserir_as_informações_de_bullying_sem_descricao() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        assertNotNull(bullyingRetornado.getId());
    }

    @Test(expected = LimiteDeObjetosAtingido.class)
    public void administrador_quer_cadastrar_mais_de_um_bullying() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        bullyingService.save(bullyingEsperado);
        bullyingService.save(bullyingEsperado);

    }


    //Testes do Editar Bullying

    @Test
    public void administrador_quer_editar_as_informações_de_bullying_com_sucesso() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        String novaDescricao = "Nova Descricao";
        String novaImagem = "Nova Imagem";
        String novoLink = "NovoLink";

        bullyingRetornado.setDescricao(novaDescricao);
        bullyingRetornado.setImagem(novaImagem);
        bullyingRetornado.setLinkDoFormulario(novoLink);

        bullyingRetornado = bullyingService.update(bullyingRetornado);

        assertEquals(novaDescricao, bullyingRetornado.getDescricao());
        assertEquals(novaImagem, bullyingRetornado.getImagem());
        assertEquals(novoLink, bullyingRetornado.getLinkDoFormulario());

    }

    @Test(expected = CampoObrigatorio.class)
    public void adminstrador_quer_editar_as_informações_sem_link() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        String novaDescricao = "Nova Descricao";
        String novaImagem = "Nova Imagem";

        bullyingRetornado.setDescricao(novaDescricao);
        bullyingRetornado.setImagem(novaImagem);
        bullyingRetornado.setLinkDoFormulario(null);

        bullyingService.update(bullyingRetornado);
    }

    @Test
    public void administrador_quer_editar_as_informações_de_bullying_sem_imagem() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        String novaDescricao = "Nova Descricao";
        String novoLink = "NovoLink";

        bullyingRetornado.setDescricao(novaDescricao);
        bullyingRetornado.setImagem(null);
        bullyingRetornado.setLinkDoFormulario(novoLink);

        bullyingRetornado = bullyingService.update(bullyingRetornado);

        assertNull(bullyingRetornado.getImagem());
    }

    @Test
    public void administrador_quer_editar_as_informações_de_bullying_sem_descricao() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        Bullying bullyingRetornado = bullyingService.save(bullyingEsperado);

        String novaImagem = "Nova Imagem";
        String novoLink = "NovoLink";

        bullyingRetornado.setImagem(novaImagem);
        bullyingRetornado.setDescricao(null);
        bullyingRetornado.setLinkDoFormulario(novoLink);

        bullyingRetornado = bullyingService.update(bullyingRetornado);

        assertNull(bullyingRetornado.getDescricao());
    }

    @Test
    public void aluno_quer_visualizar_formulario_de_bullying() throws LimiteDeObjetosAtingido, CampoObrigatorio, ObjetoNaoEncontrado {
        bullyingEsperado.setDescricao(descricao);
        bullyingEsperado.setImagem(imagem);
        bullyingEsperado.setLinkDoFormulario(linkDoFormulario);

        bullyingService.save(bullyingEsperado);

        assertNotNull(bullyingService.find());

    }
}