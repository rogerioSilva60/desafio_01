package br.com.desafio.conta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.conta.entity.Contas;
import br.com.desafio.conta.repository.ContaRepository;
import br.com.desafio.conta.service.ContaService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.NotFoundException;

@Service
public class ContaServiceImpl implements ContaService{

	private ContaRepository repository;

	public ContaServiceImpl(ContaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void validarConta(Long agencia, Long numero) {
		boolean existe = repository.existsByAgenciaAndNumero(agencia, numero);
		if(existe) {
			throw new BusinessException("Conta existente");
		}
	}
	
	@Override
	public Contas salvar(Contas conta) {
		Contas contaSalva = repository.save(conta);
		return contaSalva;
	}

	@Override
	public Contas buscar(Empresas empresa, Long id) {
		Contas contas = repository.findByEmpresaAndId(empresa, id)
			.orElseThrow(() -> new NotFoundException("Conta corrente da empresa não encontrada"));
		return contas;
	}

	@Override
	public List<Contas> buscar(Empresas empresa) {
		List<Contas> lista = repository.findByEmpresaNotFuncionarios(empresa.getId());
		return lista;
	}

	@Override
	public Contas buscar(Long agenciaOrigem, Long numeroOrigem) {
		Contas contaEmpresa = repository.findByAgenciaAndNumero(agenciaOrigem, numeroOrigem)
			.orElseThrow(() -> new NotFoundException("Conta corrente da empresa não encontrada"));
		return contaEmpresa;
	}

	@Override
	public Contas atualizar(Contas contaEmpresa) {
		if(contaEmpresa.getId() == null || contaEmpresa.getId() < 1) {
			new BusinessException("Id da conta da empresa obrigatório para atualizar");
		}
		Contas contaAtualizada = repository.save(contaEmpresa);
		return contaAtualizada;
	}
}
