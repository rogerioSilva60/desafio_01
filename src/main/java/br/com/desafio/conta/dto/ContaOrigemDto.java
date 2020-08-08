package br.com.desafio.conta.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaOrigemDto {

	@NotNull(message = "Obrigatório agencia")
	private Long agencia;
	@NotNull(message = "Obrigatório numero")
	private Long numero;
}
