package br.com.desafio.empresa.service;

import br.com.desafio.empresa.entity.Empresas;

public interface EmpresaService {

	Empresas salvar(Empresas empresa, Long idUser);

	Empresas buscar(Long id, Long idUser);

}
