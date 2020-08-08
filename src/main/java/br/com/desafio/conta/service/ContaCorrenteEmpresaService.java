package br.com.desafio.conta.service;

import java.util.List;

import br.com.desafio.conta.entity.ContaCorrenteEmpresa;
import br.com.desafio.empresa.entity.Empresas;

public interface ContaCorrenteEmpresaService {

	ContaCorrenteEmpresa salvar(ContaCorrenteEmpresa contaCorrente);

	ContaCorrenteEmpresa buscar(Empresas empresas, Long id);

	List<ContaCorrenteEmpresa> buscar(Empresas empresa);

	ContaCorrenteEmpresa buscar(Long agenciaOrigem, Long numeroOrigem);

	ContaCorrenteEmpresa atualizar(ContaCorrenteEmpresa contaEmpresa);

}
