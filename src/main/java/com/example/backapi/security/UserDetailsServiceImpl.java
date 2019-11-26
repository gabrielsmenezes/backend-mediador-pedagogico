package com.example.backapi.security;

import com.example.backapi.usuario.Usuario;
import com.example.backapi.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username){
		Usuario cli = repo.findByUsername(username);
		if (cli == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSS(cli.getId(), cli.getUsername(), cli.getSenha(), cli.getPerfis());
	}
}
