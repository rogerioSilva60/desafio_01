package br.com.desafio.conta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.usuario.entity.Usuarios;

public interface ContaCorrenteFuncionarioRepository extends JpaRepository<ContaCorrenteFuncionario, Long>{

	Optional<ContaCorrenteFuncionario> findByFuncionario(Funcionarios funcionario);

	boolean existsByFuncionario(Funcionarios funcionario);

	Optional<ContaCorrenteFuncionario> findByAgenciaAndNumero(Long agenciaFavorecido, Long numeroFavorecido);

}
