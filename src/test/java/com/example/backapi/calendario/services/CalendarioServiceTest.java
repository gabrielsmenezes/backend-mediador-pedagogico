package com.example.backapi.calendario.services;

import com.example.backapi.calendario.domain.Calendario;
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
public class CalendarioServiceTest {

    @Autowired
    CalendarioService calendarioService;

    Calendario calendario;
    String linkDoCalendario;

    @Before
    public void setUp() throws Exception {

        calendario = new Calendario();
        linkDoCalendario = "https://calendar.google.com/calendar/r";
    }

//[US-39] Cadastrar Calendario

    @Test
    public void administrador_quer_cadastrar_as_informacoes_do_calendario() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendario.setLinkDoCalendario(linkDoCalendario);
        Calendario calendarioRetornado = calendarioService.save(calendario);
        assertNotNull(calendarioRetornado.getId());
        assertEquals(linkDoCalendario, calendarioRetornado.getLinkDoCalendario());
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informacoes_sem_link() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendario.setLinkDoCalendario(null);
        calendarioService.save(calendario);

    }

    @Test (expected = LimiteDeObjetosAtingido.class)
    public void administrador_quer_cadastrar_mais_de_um_calendario() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendario.setLinkDoCalendario(linkDoCalendario);
        calendarioService.save(calendario);
        calendario.setId(null);
        calendarioService.save(calendario);
    }

//[US-40] Editar Calendário - Web

    @Test
    public void administrador_quer_editar_as_informacoes_do_calendario() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendario.setLinkDoCalendario(linkDoCalendario);
        Calendario calendarioRetornado = calendarioService.save(calendario);
        String novoLink = "www.novolink.com";
        calendarioRetornado.setLinkDoCalendario(novoLink);
        calendarioRetornado = calendarioService.update(calendarioRetornado);
        assertEquals(novoLink, calendarioRetornado.getLinkDoCalendario());
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_as_informacoes_sem_link() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        calendario.setLinkDoCalendario(linkDoCalendario);
        calendario = calendarioService.save(calendario);
        calendario.setLinkDoCalendario(null);
        calendarioService.update(calendario);
    }

    //[US-20] Visualizar o calendario da escola

    @Test
    public void aluno_quer_listar_calendario() throws LimiteDeObjetosAtingido, CampoObrigatorio, ObjetoNaoEncontrado {
        calendario.setLinkDoCalendario(linkDoCalendario);
        Calendario calendarioEsperado = calendarioService.save(calendario);
        Calendario calendarioRetornado = calendarioService.find();

        assertEquals(calendarioEsperado, calendarioRetornado);
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void aluno_quer_listar_calendario_ainda_nao_cadastrado() throws ObjetoNaoEncontrado {
        calendarioService.find();
    }

}