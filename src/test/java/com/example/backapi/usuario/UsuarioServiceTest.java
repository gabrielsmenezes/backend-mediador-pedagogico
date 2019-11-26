package com.example.backapi.usuario;

import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void find() throws ObjectNotFoundException {

        assertNotNull(usuarioService.find(1));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void delete() throws Exception {
        usuarioService.delete(1);
        assertNull(usuarioService.find(1));
    }

    @Test
    public void findAll() {
        Set<Integer> perfis = new HashSet<>();
        perfis.add(Perfil.ADMIN.getCod());
        Usuario usuario = usuarioService.insert(new Usuario(null, "gabriel", "gabriel", perfis));
        assertNotNull(usuario.getId());
    }
}