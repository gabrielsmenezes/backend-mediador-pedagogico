package com.example.backapi.security;

import com.example.backapi.usuario.Usuario;
import com.example.backapi.usuario.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserDetailsServiceImplTest {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsername() {
        Usuario usuarioEsperado = repo.findAll().get(0);
        UserDetails usuarioRetornado = userDetailsService.loadUserByUsername(usuarioEsperado.getUsername());
        assertEquals(usuarioEsperado.getUsername(), usuarioRetornado.getUsername());
    }
}