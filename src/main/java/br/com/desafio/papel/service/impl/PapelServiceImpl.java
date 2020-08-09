package br.com.desafio.papel.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.papel.entity.Papeis;
import br.com.desafio.papel.repository.PapelRepository;
import br.com.desafio.papel.service.PapelService;

@Service
public class PapelServiceImpl implements PapelService {

	private PapelRepository repository;

	public PapelServiceImpl(PapelRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Papeis> buscar() {
		List<Papeis> lista = repository.findAll();
		return lista;
	}
	
	
}
