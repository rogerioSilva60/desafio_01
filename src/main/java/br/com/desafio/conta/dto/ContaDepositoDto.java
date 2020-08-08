package br.com.desafio.conta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaDepositoDto {

	@Valid
	@NotNull(message = "Obrigatório origem")
	private ContaOrigemDto origem;
	
	@NotNull(message = "Obrigatório valor")
	private double valor;
}
