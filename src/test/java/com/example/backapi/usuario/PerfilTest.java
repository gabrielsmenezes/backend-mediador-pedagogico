package com.example.backapi.usuario;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerfilTest {

    @Test
    public void getCod() {
        assertEquals( 1, Perfil.ADMIN.getCod());
    }

    @Test
    public void getDescricao() {
        assertEquals("ROLE_CLIENTE", Perfil.CLIENTE.getDescricao());
    }

    @Test
    public void toEnum() {
        assertEquals(Perfil.ADMIN, Perfil.toEnum(1));
    }
}