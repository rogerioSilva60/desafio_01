package br.com.desafio.funcionario.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.util.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Funcionarios {

	@Id
	@SequenceGenerator(name = "funcionarios_id_seq", sequenceName = "funcionarios_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionarios_id_seq")
	@Column(columnDefinition = "bigserial")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String rg;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column
	private Date nascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean ativo = true;
	
	@Column(columnDefinition = "timestamp without time zone default now()")
	private Timestamp dataCriacao = new Timestamp(new Date().getTime());
	
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = false) 
	private Empresas empresa;

	public Funcionarios(Long id) {
		this.id = id;
	}
	
	
}
