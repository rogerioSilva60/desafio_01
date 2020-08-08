package br.com.desafio.conta.service.impl;

import org.springframework.stereotype.Service;

import br.com.desafio.conta.repository.ContaRepository;
import br.com.desafio.conta.service.ContaService;
import br.com.desafio.util.exception.BusinessException;

@Service
public class ContaServiceImpl implements ContaService{

	private ContaRepository repository;

	public ContaServiceImpl(ContaRepository repository) {
		this.repository = repository;
	}
	
	public void validarConta(Long agencia, Long numero) {
		boolean existe = repository.existsByAgenciaAndNumero(agencia, numero);
		if(existe) {
			throw new BusinessException("Conta existente");
		}
	}
}
