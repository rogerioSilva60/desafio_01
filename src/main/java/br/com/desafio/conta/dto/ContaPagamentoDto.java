package br.com.desafio.conta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaPagamentoDto {

	@Valid
	@NotNull(message = "Obrigatório favorecido")
	private ContaFavorecidoDto favorecido;
	
	@Valid
	@NotNull(message = "Obrigatório origem")
	private ContaOrigemDto origem;
	
	@NotNull(message = "Obrigatório valor")
	private double valor;
}
