package com.example.backapi.biblioteca.services;

import com.example.backapi.biblioteca.domain.Topico;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TopicoServiceTest {


    @Autowired
    TopicoService topicoService;

    Topico topico;

    @Before
    public void setUp() throws Exception {
        topico = new Topico();
    }

    @Test
    public void administrador_quer_cadastrar_topicosda_biblioteca_com_sucesso() throws CampoObrigatorio {
        String nomeEsperado = "Video-aula";
        topico.setNome(nomeEsperado);
        topico = topicoService.save(topico);
        assertNotNull(topico.getId());
        assertEquals(nomeEsperado, topico.getNome());
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informações_sem_nome() throws CampoObrigatorio {
        topicoService.save(topico);
    }

    @Test
    public void administrador_quer_editar_topico_da_biblioteca_com_sucesso() throws CampoObrigatorio, ObjetoNaoEncontrado {
        String nomeEsperado = "Video-aula";
        topico.setNome(nomeEsperado);
        topico = topicoService.save(topico);

        String novoNome = "PDFs";
        topico.setNome(novoNome);
        topico = topicoService.update(topico);

        assertEquals(novoNome, topico.getNome());
    }
    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_topico_sem_nome() throws CampoObrigatorio, ObjetoNaoEncontrado {
        String nomeEsperado = "Video-aula";
        topico.setNome(nomeEsperado);
        topico = topicoService.save(topico);

        topico.setNome(null);

        topicoService.update(topico);
    }

    @Test
    public void administrador_quer_listar_os_topicos_cadastrados() throws CampoObrigatorio {

        List<Topico> topicosEsperados = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            topico.setId(null);
            String nomeEsperado = "Video-aula";
            topico.setNome(nomeEsperado);
            topicoService.save(topico);
            topicosEsperados.add(topico);
        }

        List<Topico> topicos = topicoService.findAll();

        for (Topico topico : topicosEsperados) {
            assertTrue(topicos.contains(topico));
        }
    }

}