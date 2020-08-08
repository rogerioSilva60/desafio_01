package br.com.desafio.funcionario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.funcionario.repository.FuncionarioRepository;
import br.com.desafio.funcionario.service.FuncionarioService;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.NotFoundException;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private FuncionarioRepository repository;

	public FuncionarioServiceImpl(FuncionarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Funcionarios salvar(Funcionarios funcionario) {
		if(existe(funcionario.getCpf())) {
			throw new BusinessException("Cpf existente");
		}
		Funcionarios funcioanrioSalvo = repository.save(funcionario);
		return funcioanrioSalvo;
	}
	
	public boolean existe(String cpf) {
		boolean existe = repository.existsByCpf(cpf);
		return existe;
	}

	@Override
	public Funcionarios buscar(Long id, Long idEmpresa) {
		Funcionarios funcionario = repository.findByIdAndEmpresa(id, new Empresas(idEmpresa))
			.orElseThrow(() -> new NotFoundException("Funcionario nao encontrado"));
		return funcionario;
	}

	@Override
	public List<Funcionarios> buscar(Long idEmpresa) {
		List<Funcionarios> funcionarios = repository.findByEmpresa(new Empresas(idEmpresa));
		return funcionarios;
	}
	
}
