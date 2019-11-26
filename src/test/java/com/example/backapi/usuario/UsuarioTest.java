package com.example.backapi.usuario;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UsuarioTest {

    Usuario usuario;

    @Before
    public void setUp() throws Exception {
        usuario = new Usuario();
    }

    @Test
    public void deve_ter_username(){
        String username = "admin";
        usuario.setUsername(username);
        assertEquals(username, usuario.getUsername());
    }

    @Test
    public void deve_ter_senha(){
        String senha = "admin";
        usuario.setSenha(senha);
        assertEquals(senha, usuario.getSenha());
    }

    @Test
    public void deve_ter_perfis(){
        Set<Integer> perfils = new HashSet<>();
        perfils.add(Perfil.ADMIN.getCod());
        usuario.setPerfis(perfils);
        Set<Perfil> esperados = perfils.stream().map(Perfil::toEnum).collect(Collectors.toSet());
        assertEquals(esperados, usuario.getPerfis());
    }

}