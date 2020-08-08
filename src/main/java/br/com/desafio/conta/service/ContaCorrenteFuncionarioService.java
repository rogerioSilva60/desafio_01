package br.com.desafio.conta.service;

import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.funcionario.entity.Funcionarios;

public interface ContaCorrenteFuncionarioService {

	ContaCorrenteFuncionario salvar(ContaCorrenteFuncionario contaCorrente);

	ContaCorrenteFuncionario buscar(Funcionarios funcionarios);

	ContaCorrenteFuncionario buscar(Long agenciaFavorecido, Long numeroFavorecido);

	ContaCorrenteFuncionario atualizar(ContaCorrenteFuncionario contaFuncionario);

}
