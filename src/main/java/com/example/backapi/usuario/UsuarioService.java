package com.example.backapi.usuario;

import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Usuario insert(Usuario usuario) {
		usuario.setSenha(pe.encode(usuario.getSenha()));
		return repo.save(usuario);
	}
	

	public void delete(Integer id) throws Exception {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new Exception("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}

}
