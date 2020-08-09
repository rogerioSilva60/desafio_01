package br.com.desafio.empresa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.empresa.repository.EmpresaRepository;
import br.com.desafio.empresa.service.EmpresaService;
import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.NotFoundException;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private EmpresaRepository repository;

	public EmpresaServiceImpl(EmpresaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Empresas salvar(Empresas empresa, Long idUsuario) {
		if(exists(empresa.getCnpj())) {
			throw new BusinessException("Cnpj existetnte");
		}
		empresa.setUsuario(new Usuarios(idUsuario));
		Empresas empresaSalva = repository.save(empresa);
		return empresaSalva;
	}

	public boolean exists(String cnpj) {
		boolean exists = repository.existsByCnpj(cnpj);
		return exists;
	}

	@Override
	public Empresas buscar(Long id, Long idUsuario) {
		Empresas empresa = repository.findByIdAndUsuario(id, new Usuarios(idUsuario))
			.orElseThrow(() -> new NotFoundException("Empresa nao encontrada"));
		return empresa;
	}

	@Override
	public List<Empresas> buscar(Usuarios usuario) {
		List<Empresas> findByUsuario = repository.findByUsuario(usuario);
		return findByUsuario;
	}
	
}
