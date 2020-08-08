package br.com.desafio.jwt.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.jwt.JwtService;
import br.com.desafio.jwt.JwtUsuario;
import br.com.desafio.jwt.JwtUsuarioServiceDetailsImpl;
import br.com.desafio.jwt.dto.CredenciaisDto;
import br.com.desafio.jwt.dto.TokenDto;
import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.util.response.Response;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

	private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
	
	@Autowired
	private JwtUsuarioServiceDetailsImpl usuarioServiceDetailsImpl;
	private ModelMapper modelMapper;
	private final JwtService jwtService;
	
	public AutenticacaoController(ModelMapper modelMapper, JwtService jwtService) {
		this.modelMapper = modelMapper;
		this.jwtService = jwtService;
	}
	
	@PostMapping
	public Response<TokenDto> auth(@RequestBody @Valid CredenciaisDto dto) throws Exception {
		Response<TokenDto> response = new Response<>();
		Usuarios usuario = modelMapper.map(dto, Usuarios.class);
		JwtUsuario jwtUser = (JwtUsuario) usuarioServiceDetailsImpl.authenticate(usuario);
		String token = jwtService.getToken(jwtUser);
		TokenDto tokenDto = new TokenDto(token);
		response.setData(tokenDto);
		return response;
	}
}
