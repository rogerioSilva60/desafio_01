package br.com.desafio.funcionario.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FuncionarioEmpresaDto {

	@NotNull(message = "Obrigatório id")
	private Long id;
}
