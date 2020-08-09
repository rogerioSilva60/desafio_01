package br.com.desafio.conta.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.desafio.funcionario.entity.Funcionarios;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CCF")
@Table(name = "conta")
public class ContaCorrenteFuncionario extends Contas {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinTable(name = "conta_corrente_funcionarios", uniqueConstraints = @UniqueConstraint(
				columnNames = {"id_conta_corrente", "id_funcionario"}, name = "unique_conta_corrente_funcionario"),
	joinColumns = @JoinColumn(name="id_conta_corrente", nullable = false, referencedColumnName = "id", table = "conta_corrente_funcionario",
		foreignKey = @ForeignKey(name="conta_corrente_fk", value = ConstraintMode.CONSTRAINT)),
	inverseJoinColumns = @JoinColumn(name="id_funcionario", nullable = false,referencedColumnName = "id", table = "funcionarios", 
		foreignKey = @ForeignKey(name="funcionario_fk", value = ConstraintMode.CONSTRAINT)))
	private Funcionarios funcionario;
}
