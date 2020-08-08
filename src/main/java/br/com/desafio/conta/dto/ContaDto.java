package br.com.desafio.conta.dto;

import lombok.Data;

@Data
public class ContaDto {

	private Long id;
	private double saldo;
	private Long agencia;
	private Long numero;
	
}
