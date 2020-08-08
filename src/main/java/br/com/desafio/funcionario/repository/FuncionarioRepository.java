package br.com.desafio.funcionario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;

public interface FuncionarioRepository extends JpaRepository<Funcionarios, Long>{

	boolean existsByCpf(String cpf);

	Optional<Funcionarios> findByIdAndEmpresa(Long id, Empresas empresas);

	List<Funcionarios> findByEmpresa(Empresas empresas);

}
