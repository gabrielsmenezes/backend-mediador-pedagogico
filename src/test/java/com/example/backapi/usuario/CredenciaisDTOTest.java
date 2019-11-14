package com.example.backapi.usuario;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CredenciaisDTOTest {

    CredenciaisDTO credenciaisDTO;

    @Before
    public void setUp() throws Exception {
        credenciaisDTO = new CredenciaisDTO();
    }

    @Test
    public void getUsername() {
        String username = "admin";
        credenciaisDTO.setUsername(username);
        assertEquals(username, credenciaisDTO.getUsername());
    }

    @Test
    public void getSenha() {
        String senha = "admin";
        credenciaisDTO.setSenha(senha);
        assertEquals(senha, credenciaisDTO.getSenha());
    }
}