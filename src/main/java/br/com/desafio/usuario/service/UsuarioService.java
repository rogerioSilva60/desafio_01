package br.com.desafio.usuario.service;

import br.com.desafio.usuario.entity.Usuarios;

public interface UsuarioService {

	Usuarios save(Usuarios usuario);

	Usuarios buscar(Long idUser);

}
