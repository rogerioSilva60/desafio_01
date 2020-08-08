package br.com.desafio.jwt.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CredenciaisDto {

	@NotEmpty(message = "Obrigatório login")
	private String login;
	@NotEmpty(message = "Obrigatório senha")
	private String senha;
}
