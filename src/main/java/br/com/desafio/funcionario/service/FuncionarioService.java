package br.com.desafio.funcionario.service;

import java.util.List;

import br.com.desafio.funcionario.entity.Funcionarios;

public interface FuncionarioService {

	Funcionarios salvar(Funcionarios funcionarioSalvo);

	Funcionarios buscar(Long id, Long idEmpresa);

	List<Funcionarios> buscar(Long idEmpresa);

}
