package com.example.backapi.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioService usuarioService;

	public void instantiateTestDatabase() {

		Set<Integer> perfis = new HashSet<>();
		perfis.add(1);

		Usuario cli1 = new Usuario(null, "admin",pe.encode("admin"), perfis);

		usuarioService.insert(cli1);
	}
}
