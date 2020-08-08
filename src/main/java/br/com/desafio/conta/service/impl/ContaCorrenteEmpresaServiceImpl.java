package br.com.desafio.conta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.conta.entity.ContaCorrenteEmpresa;
import br.com.desafio.conta.repository.ContaCorrenteEmpresaRepository;
import br.com.desafio.conta.service.ContaCorrenteEmpresaService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.NotFoundException;

@Service
public class ContaCorrenteEmpresaServiceImpl implements ContaCorrenteEmpresaService {
	
	private ContaCorrenteEmpresaRepository repository;

	public ContaCorrenteEmpresaServiceImpl(ContaCorrenteEmpresaRepository repository) {
		this.repository = repository;
	}

	@Override
	public ContaCorrenteEmpresa salvar(ContaCorrenteEmpresa contaCorrente) {
		ContaCorrenteEmpresa contaCorrenteSalva = repository.save(contaCorrente);
		return contaCorrenteSalva;
	}

	@Override
	public ContaCorrenteEmpresa buscar(Empresas empresa, Long id) {
		ContaCorrenteEmpresa contaCorrente = repository.findByEmpresaAndId(empresa, id)
			.orElseThrow(() -> new NotFoundException("Conta corrente da empresa não encontrada"));
		return contaCorrente;
	}

	@Override
	public List<ContaCorrenteEmpresa> buscar(Empresas empresa) {
		List<ContaCorrenteEmpresa> lista = repository.findByEmpresa(empresa);
		return lista;
	}

	@Override
	public ContaCorrenteEmpresa buscar(Long agenciaOrigem, Long numeroOrigem) {
		ContaCorrenteEmpresa contaEmpresa = repository.findByAgenciaAndNumero(agenciaOrigem, numeroOrigem)
			.orElseThrow(() -> new NotFoundException("Conta corrente da empresa não encontrada"));
		return contaEmpresa;
	}

	@Override
	public ContaCorrenteEmpresa atualizar(ContaCorrenteEmpresa contaEmpresa) {
		if(contaEmpresa.getId() == null || contaEmpresa.getId() < 1) {
			new BusinessException("Id da conta da empresa obrigatório para atualizar");
		}
		ContaCorrenteEmpresa contaAtualizada = repository.save(contaEmpresa);
		return contaAtualizada;
	}
	
	
}
