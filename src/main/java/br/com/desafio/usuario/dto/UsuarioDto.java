package br.com.desafio.usuario.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioDto {

	private Long id;
	@NotEmpty(message = "Obrigatório nome")
	private String nome;
	@NotEmpty(message = "Obrigatório email")
	private String email;
	@NotEmpty(message = "Obrigatório login")
	private String login;
	@NotEmpty(message = "Obrigatório senha")
	private String senha;
	
	@Valid
	@NotNull(message = "Obrogatório papeis")
	private Set<UsuarioPapelDto> papeis;
}
