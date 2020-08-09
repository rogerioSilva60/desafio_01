package br.com.desafio.conta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.desafio.conta.entity.Contas;
import br.com.desafio.empresa.entity.Empresas;

public interface ContaRepository extends JpaRepository<Contas, Long>{

	boolean existsByAgenciaAndNumero(Long agencia, Long numero);

	Optional<Contas> findByEmpresaAndId(Empresas empresa, Long id);

	@Query(value = "select * from contas c where c.id_empresa= ?1 and "
			+ "c.id not in (select id_conta_corrente from conta_corrente_funcionarios)", nativeQuery = true)
	List<Contas> findByEmpresaNotFuncionarios(Long idEmpresa);

	Optional<Contas> findByAgenciaAndNumero(Long agenciaOrigem, Long numeroOrigem);
}
