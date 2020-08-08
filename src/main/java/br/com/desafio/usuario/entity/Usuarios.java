package br.com.desafio.usuario.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.desafio.papel.entity.Papeis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuarios_id_seq", sequenceName = "usuarios_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_seq")
	@Column(columnDefinition = "bigserial")
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(columnDefinition = "timestamp without time zone default now()")
	private Timestamp dataCriacao = new Timestamp(new Date().getTime());
	
	@Column(columnDefinition = "Boolean default true")
	private Boolean ativo = true;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_papeis", uniqueConstraints = @UniqueConstraint(
				columnNames = {"id_usuario", "id_papel"}, name = "unique_usuario_papel"),
	joinColumns = @JoinColumn(name="id_usuario", nullable = false, referencedColumnName = "id", table = "usuarios",
		foreignKey = @ForeignKey(name="usuario_fk", value = ConstraintMode.CONSTRAINT)),
	inverseJoinColumns = @JoinColumn(name="id_papel", nullable = false,referencedColumnName = "id", table = "papeis", 
		foreignKey = @ForeignKey(name="papel_fk", value = ConstraintMode.CONSTRAINT)))
	private Set<Papeis> papeis;
	
	public String[] getArrayPapeis(){
		String[] papeis = new String[this.papeis.size()];
		Iterator<Papeis> iterator = this.papeis.iterator();
		int aux=0;
		while(iterator.hasNext()) {
			Papeis papel = iterator.next();
			papeis[aux] = papel.getNome();
			aux++;
		}
		return papeis;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ativo, dataCriacao, email, id, login, nome, senha);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(ativo, other.ativo) && Objects.equals(dataCriacao, other.dataCriacao)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ ", dataCriacao=" + dataCriacao + ", ativo=" + ativo + "]";
	}

	public Usuarios(Long id) {
		this.id = id;
	}
}
