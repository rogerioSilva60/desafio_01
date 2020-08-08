package br.com.desafio.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio.jwt.JwtService;
import br.com.desafio.jwt.JwtUsuario;

public class UtilController {

	@Autowired
    protected HttpServletRequest request;
    @Autowired
    protected JwtService jwtService;
    
    protected Long getIdUser() {
    	JwtUsuario jwtUser = jwtService.getJwtUsuario(request);
    	return jwtUser.getId();
    }
}
