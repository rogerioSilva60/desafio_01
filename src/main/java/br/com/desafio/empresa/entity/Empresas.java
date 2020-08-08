package br.com.desafio.empresa.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.desafio.usuario.entity.Usuarios;
import lombok.Data;

@Data
@Entity
@Table
public class Empresas implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "empresas_id_seq", sequenceName = "empresas_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresas_id_seq")
	@Column(columnDefinition = "bigserial")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean ativo = true;
	
	@Column(columnDefinition = "timestamp without time zone default now()")
	private Timestamp dataCriacao = new Timestamp(new Date().getTime());
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false) 
	private Usuarios usuario;
}
