package br.com.desafio.usuario.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UsuarioViewDto {

	private Long id;
	private String nome;
	private String email;
	private String login;
	
	private Set<UsuarioPapelDto> papeis;
}
