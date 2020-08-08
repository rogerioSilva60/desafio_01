package br.com.desafio.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.usuario.entity.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long>{

	Optional<Usuarios> findByLoginAndAtivo(String login, boolean ativo);

	boolean existsByLogin(String login);
}
