package br.com.desafio.conta.service;

import java.util.List;

import br.com.desafio.conta.entity.Contas;
import br.com.desafio.empresa.entity.Empresas;

public interface ContaService {

	void validarConta(Long agencia, Long numero);
	
	Contas salvar(Contas conta);
	
	Contas buscar(Empresas empresa, Long id);
	
	List<Contas> buscar(Empresas empresa);
	
	Contas buscar(Long agenciaOrigem, Long numeroOrigem);
	
	Contas atualizar(Contas contaEmpresa);
}
