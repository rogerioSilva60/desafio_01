package br.com.desafio.conta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaDto {

	private Long id;
	private double saldo;
	private Long agencia;
	private Long numero;
	
	@Valid
	@NotNull(message = "Obrigatório empresa")
	private ContaEmpresaDto empresa;
	
}
