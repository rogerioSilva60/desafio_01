package br.com.desafio.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import lombok.Data;

@Data
public class JwtUsuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUsuario(Long id, String username, String password, String... papeis) {
		this.id = id;
		this.username = username;
		this.password = password;
		papeis(papeis);
	}
	
	private void papeis(String... papeis) {
		List<GrantedAuthority> authorities = new ArrayList<>(papeis.length);
		for(String papel : papeis) {
			Assert.isTrue(!papel.startsWith("ROLE_"),
					() -> papel + " não pode começar com ROLE_ (é adicionado automaticamente)");
			authorities.add(new SimpleGrantedAuthority("ROLE_" + papel));
		}
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
