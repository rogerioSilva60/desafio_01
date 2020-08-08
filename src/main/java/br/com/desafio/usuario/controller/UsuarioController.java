package br.com.desafio.usuario.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.usuario.dto.UsuarioDto;
import br.com.desafio.usuario.dto.UsuarioViewDto;
import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.usuario.service.UsuarioService;
import br.com.desafio.util.UtilController;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController extends UtilController {

	private UsuarioService service;
	private ModelMapper modelMapper;
	
	public UsuarioController(UsuarioService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Response<String>> salvar(@RequestBody @Valid UsuarioDto dto) {
		Response<String> response = new Response<>();
		Usuarios usuario = modelMapper.map(dto, Usuarios.class);
		Usuarios usuarioSalvo = service.save(usuario);
		String token = jwtService.getToken(usuarioSalvo);
		response.setData(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<UsuarioViewDto>> buscar() {
		Response<UsuarioViewDto> response = new Response<>();
		Usuarios usuario = service.buscar(getIdUser());
		UsuarioViewDto usuarioDto = modelMapper.map(usuario, UsuarioViewDto.class);
		response.setData(usuarioDto);
		return ResponseEntity.ok(response);
	}
}
