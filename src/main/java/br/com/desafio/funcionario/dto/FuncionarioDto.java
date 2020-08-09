package br.com.desafio.funcionario.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.desafio.util.enums.Genero;
import lombok.Data;

@Data
public class FuncionarioDto {

	private Long id;
	@NotEmpty(message = "Obrigatório nome")
	private String nome;
	private String rg;
	@NotEmpty(message = "Obrigatório cpf")
	private String cpf;
	private Date nascimento;
	@NotNull(message = "Obrigatório genero")
	private Genero genero;
	
	@Valid
	@NotNull(message = "Obrigatório empresa")
	private FuncionarioEmpresaDto empresa;
}
