package br.com.desafio.funcionario.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.funcionario.dto.FuncionarioDto;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.funcionario.service.FuncionarioService;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/funcionario")
public class FuncionarioController {

	private FuncionarioService service;
	private ModelMapper modelMapper;
	
	public FuncionarioController(FuncionarioService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<FuncionarioDto>> salvar(@RequestBody @Valid FuncionarioDto dto) {
		Response<FuncionarioDto> response = new Response<>();
		Funcionarios funcionario = modelMapper.map(dto, Funcionarios.class);
		Funcionarios funcionarioSalvo = service.salvar(funcionario);
		FuncionarioDto funcionarioDto = modelMapper.map(funcionarioSalvo, FuncionarioDto.class);
		response.setData(funcionarioDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("{idEmpresa}/{id}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<FuncionarioDto>> buscar(@PathVariable Long idEmpresa, @PathVariable Long id) {
		Response<FuncionarioDto> response = new Response<>();
		Funcionarios funcionario = service.buscar(id, idEmpresa);
		FuncionarioDto funcionarioDto = modelMapper.map(funcionario, FuncionarioDto.class);
		response.setData(funcionarioDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{idEmpresa}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<List<FuncionarioDto>>> buscar(@PathVariable Long idEmpresa) {
		Response<List<FuncionarioDto>> response = new Response<>();
		List<Funcionarios> lista = service.buscar(idEmpresa);
		List<FuncionarioDto> listaDto = lista.stream()
			.map(funcionario -> modelMapper.map(funcionario, FuncionarioDto.class))
			.collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}
}
