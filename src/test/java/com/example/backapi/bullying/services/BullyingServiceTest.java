package com.example.backapi.bullying.services;

import com.example.backapi.bullying.domain.Bullying;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BullyingServiceTest {

    @Autowired
    BullyingService bullyingService;

    String descricao;
    String imagem;
    String linkDoFormulario;
    Bullying bullyingEsperado;

    @Before
    public void setUp() throws Exception {
        bullyingEsperado = new Bullying();
        descricao = "Bullying é a prática de atos violentos, intencionais e repetidos, contra uma pessoa indefesa, que podem causar danos físicos e psicológicos às vítimas. O termo surgiu a partir do inglês bully, palavra que significa tirano, brigão ou valentão, na tradução para o português";
        imagem = "imagem";
        linkDoFormulario = "www.google.com";
    }

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


}