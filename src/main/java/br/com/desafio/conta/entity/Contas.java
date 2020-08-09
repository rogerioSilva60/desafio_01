package br.com.desafio.conta.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.util.exception.BusinessException;
import lombok.Data;

@Data
@Entity
@Table
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "C")
public class Contas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "contas_id_seq", sequenceName = "contas_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contas_id_seq")
	@Column(columnDefinition = "bigserial")
	private Long id;
	
	@Column(columnDefinition = "double precision default 0")
	private double saldo;
    
	@Column(nullable = false)
	private Long agencia;
    
	@Column(nullable = false)
    private Long numero;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa", columnDefinition = "bigint null")
	private Empresas empresa;    
    
    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
        } else {
            throw new BusinessException("Não foi possível sacar, pois o valor origem esta abaixo que o valor informado");
        }
    }

    public void transferir(double valor, Contas destino) {
    	if(valor <= 0) throw new BusinessException("Não foi possível transferir, pois o valor informado esta negativo");
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor);
        } else {
        	throw new BusinessException("Não foi possível transferir, pois o valor origem esta abaixo que o valor informado");
        }
    }
}
