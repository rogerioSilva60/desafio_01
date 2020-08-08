package br.com.desafio.conta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.conta.entity.ContaCorrenteEmpresa;
import br.com.desafio.empresa.entity.Empresas;

public interface ContaCorrenteEmpresaRepository extends JpaRepository<ContaCorrenteEmpresa, Long>{

	boolean existsByAgenciaAndNumero(Long agencia, Long numero);

	Optional<ContaCorrenteEmpresa> findByEmpresaAndId(Empresas empresa, Long id);

	List<ContaCorrenteEmpresa> findByEmpresa(Empresas empresa);

	Optional<ContaCorrenteEmpresa> findByAgenciaAndNumero(Long agenciaOrigem, Long numeroOrigem);

}
