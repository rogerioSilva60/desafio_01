package br.com.desafio.conta.service;

import java.util.List;

import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;

public interface ContaCorrenteFuncionarioService {

	ContaCorrenteFuncionario salvar(ContaCorrenteFuncionario contaCorrente);

	ContaCorrenteFuncionario buscar(Empresas empresa, Funcionarios funcionario);

	ContaCorrenteFuncionario buscar(Long agenciaFavorecido, Long numeroFavorecido);

	ContaCorrenteFuncionario atualizar(ContaCorrenteFuncionario contaFuncionario);

	List<ContaCorrenteFuncionario> buscar(Empresas empresas);

}
