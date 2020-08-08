package br.com.desafio.empresa.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class EmpresaDto {

	private Long id;
	@NotEmpty(message = "Obrigatório nome")
	private String nome;
	@NotEmpty(message = "Obrigatório cnpj")
	private String cnpj;
}
