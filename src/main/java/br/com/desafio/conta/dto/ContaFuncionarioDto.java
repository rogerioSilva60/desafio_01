package br.com.desafio.conta.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaFuncionarioDto {

	@NotNull(message = "Obrigatório id")
	private Long id;
}
