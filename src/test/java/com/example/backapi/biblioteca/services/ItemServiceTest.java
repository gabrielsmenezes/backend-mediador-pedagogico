package com.example.backapi.biblioteca.services;

import com.example.backapi.biblioteca.domain.ItemTopico;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    TopicoService topicoService;

    private Topico topico;
    private ItemTopico itemTopico;

    @Before
    public void setUp() throws Exception {
        topico = new Topico();
        topico.setNome("E-books");
        itemTopico = new ItemTopico();
        topico = topicoService.save(topico);
        itemTopico.setNome("Aula de PHP");
        itemTopico.setLinkDoItem("www.youtube.com");
        itemTopico.setTopico(topico);
    }

    //[US-53] Cadastrar um item do tópico da biblioteca - Web

    @Test
    public void administrador_quer_cadastrar_as_informacoes_dos_itens_dos_topicos_da_biblioteca() throws CampoObrigatorio {
        ItemTopico itemRetornado = itemService.save(itemTopico);

        assertEquals(itemTopico, itemRetornado);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informacoes_sem_nome() throws CampoObrigatorio {
        itemTopico.setNome(null);

        itemService.save(itemTopico);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informacoes_sem_link() throws CampoObrigatorio {
        itemTopico.setLinkDoItem(null);
        itemService.save(itemTopico);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_inserir_as_informacoes_sem_topico() throws CampoObrigatorio {
        itemTopico.setTopico(null);
        itemService.save(itemTopico);
    }

    //[US-54] Editar item do topico da biblioteca - Web

    @Test
    public void administrador_quer_editar_as_informacoes_do_item_do_topico_da_biblioteca() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ItemTopico itemEsperado = itemService.save(itemTopico);
        itemEsperado.setNome("Aula de Vue");
        itemEsperado.setLinkDoItem("www.vue.com");

        ItemTopico itemObtido = itemService.update(itemTopico);

        assertEquals(itemObtido, itemEsperado);

    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_as_informacoes_sem_nome() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ItemTopico itemEsperado = itemService.save(itemTopico);
        itemEsperado.setNome(null);
        itemEsperado.setLinkDoItem("www.vue.com");
        itemService.update(itemTopico);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_as_informacoes_sem_link() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ItemTopico itemEsperado = itemService.save(itemTopico);
        itemEsperado.setNome("Aula de Vue");
        itemEsperado.setLinkDoItem(null);
        itemService.update(itemTopico);
    }

    @Test(expected = CampoObrigatorio.class)
    public void administrador_quer_editar_as_informacoes_sem_topico() throws CampoObrigatorio, ObjetoNaoEncontrado {
        ItemTopico itemEsperado = itemService.save(itemTopico);
        itemEsperado.setNome("Aula de Vue");
        itemEsperado.setLinkDoItem("www.vue.com");
        itemEsperado.setTopico(null);
        itemService.update(itemTopico);
    }

    //Deletar item de biblioteca

    @Test
    public void administrador_quer_deletar_um_item_com_sucesso() throws CampoObrigatorio, ObjetoNaoEncontrado {
        itemTopico = itemService.save(itemTopico);

        itemService.delete(itemTopico.getId());

        List<ItemTopico> itens = itemService.findAll();

        assertFalse(itens.contains(itemTopico));
    }

    @Test(expected = ObjetoNaoEncontrado.class)
    public void administrador_quer_deletar_um_item_inexistente() throws ObjetoNaoEncontrado {
        itemService.delete(-1);
    }

//Listar itens

    @Test
    public void administrador_quer_listar_todos_os_itens_de_um_topico() throws CampoObrigatorio {
        for (int i = 0; i < 10; i++) {
            ItemTopico itemRetornado = itemService.save(itemTopico);
            itemRetornado.setId(null);
        }

        assertEquals(10, itemService.findAll().size());

    }


}