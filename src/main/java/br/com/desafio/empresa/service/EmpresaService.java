package br.com.desafio.empresa.service;

import java.util.List;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.usuario.entity.Usuarios;

public interface EmpresaService {

	Empresas salvar(Empresas empresa, Long idUser);

	Empresas buscar(Long id, Long idUser);

	List<Empresas> buscar(Usuarios usuario);

}
