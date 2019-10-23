package com.example.backapi.sobre_escola.services;

import com.example.backapi.sobre_escola.domain.Escola;
import com.example.backapi.utils.exceptions.CampoObrigatorio;
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
public class EscolaServiceTest {

    @Autowired
    EscolaService escolaService;

    Escola escola;
    String nome;
    String imagem;
    String descricao;

    @Before
    public void setUp() throws Exception {
        escola = new Escola();
        nome = "Escola Estadual Dona Consuelo Muller";
        imagem = "imagem";
        descricao = "Escola Estadual Dona Consuelo Muller,Escola Estadual Dona Consuelo Muller,Escola Estadual Dona Consuelo Muller,Escola Estadual Dona Consuelo Muller";
    }



    @Test
    public void administrador_quer_cadastrar_as_informacoes_da_escola_com_sucesso() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        escola.setNome(nome);
        escola.setImagem(imagem);
        escola.setDescricao(descricao);
        escola = escolaService.save(escola);
        assertNotNull(escola.getId());
    }

    @Test(expected = CampoObrigatorio.class)
    public void adminstrador_quer_inserir_as_informacoes_sem_nome() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        escola.setNome(null);
        escola.setImagem(imagem);
        escola.setDescricao(descricao);
        escolaService.save(escola);
    }

    @Test
    public void administrador_quer_inserir_as_informacoes_da_escola_sem_imagem() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        escola.setNome(nome);
        escola.setImagem(null);
        escola.setDescricao(descricao);
        escola = escolaService.save(escola);
        assertNotNull(escola.getId());
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informacoes_da_escola_sem_descricao() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        escola.setNome(nome);
        escola.setImagem(imagem);
        escola.setDescricao(null);
        escolaService.save(escola);
    }

    @Test(expected = LimiteDeObjetosAtingido.class)
    public void administrador_quer_cadastrar_mais_de_um_sobre_a_escola() throws CampoObrigatorio, LimiteDeObjetosAtingido {
        escola.setNome(nome);
        escola.setImagem(imagem);
        escola.setDescricao(descricao);
        escola = escolaService.save(escola);
        escola.setId(null);
        escola = escolaService.save(escola);
    }


}