package com.example.backapi.aula_invertida.services;

import com.example.backapi.aula_invertida.domain.Link;
import com.example.backapi.aula_invertida.domain.Material;
import com.example.backapi.aula_invertida.domain.TipoDeLink;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    MaterialService materialService;

    @Before
    public void setUp() throws Exception {



    }

    @Test
    public void aluno_quer_visualizar_lista_de_materiais(){
        //arrange
        Material material = new Material("Titulo", "Descricao", Arrays.asList(
            new Link(TipoDeLink.VIDEO.getCodigo(), "youtube.com")
        ));
        materialService.save(material);

        //action
        Page<Material> retorno = materialService.findPage(0, 10, "id", "ASC");
        //assert

        assertEquals(retorno.getTotalElements() , 1);

    }

}