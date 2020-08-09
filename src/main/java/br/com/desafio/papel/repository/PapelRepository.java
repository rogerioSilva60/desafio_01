package br.com.desafio.papel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.papel.entity.Papeis;

public interface PapelRepository extends JpaRepository<Papeis, Long>{

	Optional<Papeis> findByNome(String string);

	boolean existsByNome(String string);

}
