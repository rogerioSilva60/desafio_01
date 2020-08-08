package br.com.desafio.usuario.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioPapelDto {

	@NotNull(message = "Obrigatório id")
	private Long id;
}
