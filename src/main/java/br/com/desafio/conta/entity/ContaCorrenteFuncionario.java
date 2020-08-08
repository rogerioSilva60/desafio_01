package br.com.desafio.conta.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.desafio.funcionario.entity.Funcionarios;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CCF")
@Table(name = "conta")
public class ContaCorrenteFuncionario extends Contas {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "id_funcionario", columnDefinition = "bigint null")
	private Funcionarios funcionario;
}
