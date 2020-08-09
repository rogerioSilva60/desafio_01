package br.com.desafio.conta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.usuario.entity.Usuarios;

public interface ContaCorrenteFuncionarioRepository extends JpaRepository<ContaCorrenteFuncionario, Long>{

	Optional<ContaCorrenteFuncionario> findByAgenciaAndNumero(Long agenciaFavorecido, Long numeroFavorecido);

	Optional<ContaCorrenteFuncionario> findByEmpresaAndFuncionario(Empresas empresa, Funcionarios funcionario);

	@Query("select c from ContaCorrenteFuncionario c inner join c.funcionario where c.empresa= ?1")
	List<ContaCorrenteFuncionario> findByEmpresa(Empresas empresa);

	boolean existsByFuncionarioAndEmpresa(Funcionarios funcionario, Empresas empresa);

}
