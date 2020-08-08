package br.com.desafio.empresa.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmpresaUsuarioDto {

	@NotNull(message = "Obrigatório id")
	private Long id;
}
