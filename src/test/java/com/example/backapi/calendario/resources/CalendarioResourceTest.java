package com.example.backapi.calendario.resources;

import com.example.backapi.calendario.domain.CalendarioDTO;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
import com.example.backapi.utils.exceptions.LimiteDeObjetosAtingido;
import com.example.backapi.utils.exceptions.ObjetoNaoEncontrado;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CalendarioResourceTest {

    @Autowired
    CalendarioResource calendarioResource;

    CalendarioDTO calendarioDTO;

    String linkDoCalendario;

    @Before
    public void setUp() throws Exception {
        calendarioDTO = new CalendarioDTO();
        linkDoCalendario = "https://calendar.google.com/calendar/r";
    }

    @Test
    public void save() throws LimiteDeObjetosAtingido, CampoObrigatorio {
        calendarioDTO.setLinkDoCalendario(linkDoCalendario);

        ResponseEntity<CalendarioDTO> resposta = calendarioResource.save(calendarioDTO);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(linkDoCalendario, resposta.getBody().getLinkDoCalendario());

    }

    @Test
    public void update() throws CampoObrigatorio {
        calendarioDTO.setLinkDoCalendario(linkDoCalendario);
        ResponseEntity<CalendarioDTO> resposta = calendarioResource.update(calendarioDTO);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(linkDoCalendario, resposta.getBody().getLinkDoCalendario());
    }

    @Test
    public void find() throws LimiteDeObjetosAtingido, CampoObrigatorio, ObjetoNaoEncontrado {
        calendarioDTO.setLinkDoCalendario(linkDoCalendario);
        calendarioDTO = calendarioResource.save(calendarioDTO).getBody();
        ResponseEntity<CalendarioDTO> retorno = calendarioResource.find();
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
        assertEquals(calendarioDTO.getId(), retorno.getBody().getId());
    }
}