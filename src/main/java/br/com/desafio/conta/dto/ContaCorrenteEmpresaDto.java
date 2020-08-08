package br.com.desafio.conta.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContaCorrenteEmpresaDto extends ContaDto {

	@Valid
	@NotNull(message = "Obrigatório empresa")
	private ContaEmpresaDto empresa;
}
