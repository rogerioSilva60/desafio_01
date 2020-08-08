package br.com.desafio.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.conta.entity.Contas;

public interface ContaRepository extends JpaRepository<Contas, Long>{

	boolean existsByAgenciaAndNumero(Long agencia, Long numero);

}
