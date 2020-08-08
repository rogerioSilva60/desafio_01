package br.com.desafio.conta.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.desafio.empresa.entity.Empresas;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("CCE")
@Table(name = "conta")
public class ContaCorrenteEmpresa extends Contas {
	 
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_empresa", columnDefinition = "bigint null")
	private Empresas empresa;
}
