package com.example.backapi.security;

import com.example.backapi.usuario.Perfil;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserSSTest {

    Integer ID = 1;
    UserSS userSS;
    private String login;
    private String senha;

    @Before
    public void setUp() throws Exception {
        Set<Perfil> perfis = new HashSet<>();
        perfis.add(Perfil.ADMIN);
        login = "admin";
        senha = "admin";
        userSS = new UserSS(ID, login, senha, perfis);
    }

    @Test
    public void getId() {

        assertEquals(ID, userSS.getId());

    }

    @Test
    public void getAuthorities() {

        assertNotNull(userSS.getAuthorities());

    }

    @Test
    public void getPassword() {

        assertEquals(senha, userSS.getPassword());

    }

    @Test
    public void getUsername() {

        assertEquals(login, userSS.getUsername());

    }

    @Test
    public void isAccountNonExpired() {
        assertTrue(userSS.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() {
        assertTrue(userSS.isAccountNonLocked());

    }

    @Test
    public void isCredentialsNonExpired() {
        assertTrue(userSS.isCredentialsNonExpired());

    }

    @Test
    public void isEnabled() {
        assertTrue(userSS.isEnabled());

    }
}