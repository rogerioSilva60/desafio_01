package br.com.desafio.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.usuario.repository.UsuarioRepository;
import br.com.desafio.util.PasswordUtil;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.CredentialsException;

@Service
public class JwtUsuarioServiceDetailsImpl implements UserDetailsService {

	private UsuarioRepository repository;
	
	public JwtUsuarioServiceDetailsImpl(UsuarioRepository repository) {
		this.repository = repository;
	}	
	
	public UserDetails authenticate(Usuarios usuario) throws CredentialsException {
		try {
			UserDetails userDetails = loadUserByUsername(usuario.getLogin());
			boolean isValid = PasswordUtil.matches(usuario.getSenha(), userDetails.getPassword());
			if (!isValid) {
				throw new BusinessException("Senha invalida");
			}
			return userDetails;
		} catch (RuntimeException e) {
			throw new CredentialsException("Login ou senha incorreto");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = repository.findByLoginAndAtivo(username, true)
		.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		JwtUsuario jwtUsuario = new JwtUsuario(usuario.getId(), usuario.getLogin(), usuario.getSenha(), usuario.getArrayPapeis());
		return jwtUsuario;
	}

}
