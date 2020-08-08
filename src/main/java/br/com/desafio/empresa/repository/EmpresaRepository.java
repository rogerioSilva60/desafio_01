package br.com.desafio.empresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.usuario.entity.Usuarios;

public interface EmpresaRepository extends JpaRepository<Empresas, Long>{

	boolean existsByCnpj(String cnpj);

	Optional<Empresas> findByIdAndUsuario(Long id, Usuarios usuarios);

}
