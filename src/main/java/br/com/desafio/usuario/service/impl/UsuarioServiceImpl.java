package br.com.desafio.usuario.service.impl;

import org.springframework.stereotype.Service;

import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.usuario.repository.UsuarioRepository;
import br.com.desafio.usuario.service.UsuarioService;
import br.com.desafio.util.PasswordUtil;
import br.com.desafio.util.exception.BusinessException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Usuarios save(Usuarios usuario) {
		if(existe(usuario.getLogin())) {
			throw new BusinessException("Login existente");
		}
		String passwordEncrypted = PasswordUtil.encode(usuario.getSenha());
		usuario.setSenha(passwordEncrypted);
		Usuarios usuarioSalvo = repository.save(usuario);
		return usuarioSalvo;
	}

	public boolean existe(String login) {
		boolean exists = repository.existsByLogin(login);
		return exists;
	}

	@Override
	public Usuarios buscar(Long idUser) {
		Usuarios usuario = repository.findById(idUser)
			.orElseThrow(() -> new BusinessException("Usuario nao encontrado"));
		return usuario;
	}
	
	
}
