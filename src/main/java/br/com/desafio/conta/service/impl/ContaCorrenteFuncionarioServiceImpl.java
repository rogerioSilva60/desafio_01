package br.com.desafio.conta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.conta.repository.ContaCorrenteFuncionarioRepository;
import br.com.desafio.conta.service.ContaCorrenteFuncionarioService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.NotFoundException;

@Service
public class ContaCorrenteFuncionarioServiceImpl implements ContaCorrenteFuncionarioService {

	private ContaCorrenteFuncionarioRepository repository;

	public ContaCorrenteFuncionarioServiceImpl(ContaCorrenteFuncionarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ContaCorrenteFuncionario salvar(ContaCorrenteFuncionario contaCorrente) {
		validar(contaCorrente.getFuncionario(), contaCorrente.getEmpresa());
		ContaCorrenteFuncionario contaCorrenteSalva = repository.save(contaCorrente);
		return contaCorrenteSalva;
	}
	
	public void validar(Funcionarios funcionario, Empresas empresa) {
		boolean existe = repository.existsByFuncionarioAndEmpresa(funcionario, empresa);
		if(existe) {
			throw new BusinessException("Funcionario existente");
		}
	}

	@Override
	public ContaCorrenteFuncionario buscar(Empresas empresa, Funcionarios funcionario) {
		ContaCorrenteFuncionario contaCorrente = repository.findByEmpresaAndFuncionario(empresa, funcionario)
			.orElseThrow(() -> new NotFoundException("Conta corrente do funcionario não encontrada"));
		return contaCorrente;
	}

	@Override
	public ContaCorrenteFuncionario buscar(Long agenciaFavorecido, Long numeroFavorecido) {
		ContaCorrenteFuncionario contaFuncionario = repository.findByAgenciaAndNumero(agenciaFavorecido, numeroFavorecido)
			.orElseThrow(() -> new NotFoundException("Conta corrente do funcionario não encontrada"));
		return contaFuncionario;
	}

	@Override
	public ContaCorrenteFuncionario atualizar(ContaCorrenteFuncionario contaFuncionario) {
		if(contaFuncionario.getId() == null || contaFuncionario.getId() < 1) {
			new BusinessException("Id da conta do funcionário obrigatório para atualizar");
		}
		ContaCorrenteFuncionario funcionarioAtualizado = repository.save(contaFuncionario);
		return funcionarioAtualizado;
	}

	@Override
	public List<ContaCorrenteFuncionario> buscar(Empresas empresa) {
		List<ContaCorrenteFuncionario> lista = repository.findByEmpresa(empresa);
		return lista;
	}
	
	
}
