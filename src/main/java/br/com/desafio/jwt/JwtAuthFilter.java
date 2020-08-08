package br.com.desafio.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


public class JwtAuthFilter extends OncePerRequestFilter {

	 private static final String AUTH_HEADER = "Authorization";
	 private static final String BEARER_PREFIX = "Bearer ";
	 
	private JwtService jwtService;
	private JwtUsuarioServiceDetailsImpl usuarioServiceDetailsImpl;

	public JwtAuthFilter(JwtService jwtService, JwtUsuarioServiceDetailsImpl usuarioServiceDetailsImpl) {
		this.jwtService = jwtService;
		this.usuarioServiceDetailsImpl = usuarioServiceDetailsImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader(AUTH_HEADER);
		if(token != null && token.startsWith(BEARER_PREFIX)) {
			token = token.startsWith(BEARER_PREFIX) ? token.substring(7) : null;
			boolean isValid = jwtService.tokenIsValid(token);
			if(isValid) {
				String login = jwtService.getLoginUsuario(token);
				UserDetails username = usuarioServiceDetailsImpl.loadUserByUsername(login);
				UsernamePasswordAuthenticationToken user = 
						new UsernamePasswordAuthenticationToken(username, null, username.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		filterChain.doFilter(request, response);
	}

}
