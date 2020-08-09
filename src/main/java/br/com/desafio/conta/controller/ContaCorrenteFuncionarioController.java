package br.com.desafio.conta.controller;

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

import br.com.desafio.conta.dto.ContaCorrenteFuncionarioDto;
import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.conta.service.ContaCorrenteFuncionarioService;
import br.com.desafio.conta.service.ContaService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.funcionario.entity.Funcionarios;
import br.com.desafio.funcionario.service.FuncionarioService;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/conta/corrente/funcionario")
public class ContaCorrenteFuncionarioController {

	private ContaCorrenteFuncionarioService service;
	private ModelMapper modelMapper;
	private ContaService contaService;
	private FuncionarioService funcionarioService;

	public ContaCorrenteFuncionarioController(ContaCorrenteFuncionarioService service, ModelMapper modelMapper,
			ContaService contaService, FuncionarioService funcionarioService) {
		this.service = service;
		this.modelMapper = modelMapper;
		this.contaService = contaService;
		this.funcionarioService = funcionarioService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteFuncionarioDto>> salvar(
			@RequestBody @Valid ContaCorrenteFuncionarioDto dto) {
		Response<ContaCorrenteFuncionarioDto> response = new Response<>();
		ContaCorrenteFuncionario contaCorrente = modelMapper.map(dto, ContaCorrenteFuncionario.class);
		funcionarioService.buscar(dto.getFuncionario().getId(), dto.getEmpresa().getId());
		contaService.validarConta(contaCorrente.getAgencia(), contaCorrente.getNumero());
		ContaCorrenteFuncionario contaCorrenteSalva = service.salvar(contaCorrente);
		ContaCorrenteFuncionarioDto contaCorrenteDto = modelMapper.map(contaCorrenteSalva,
				ContaCorrenteFuncionarioDto.class);
		response.setData(contaCorrenteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("{idEmpresa}/{idFuncionario}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteFuncionarioDto>> buscar(@PathVariable Long idEmpresa,
			@PathVariable Long idFuncionario) {
		Response<ContaCorrenteFuncionarioDto> response = new Response<>();
		ContaCorrenteFuncionario contaCorrente = service.buscar(new Empresas(idEmpresa), new Funcionarios(idFuncionario));
		ContaCorrenteFuncionarioDto contaCorrenteDto = modelMapper.map(contaCorrente,
				ContaCorrenteFuncionarioDto.class);
		response.setData(contaCorrenteDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("{idEmpresa}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<List<ContaCorrenteFuncionarioDto>>> buscar(@PathVariable Long idEmpresa) {
		Response<List<ContaCorrenteFuncionarioDto>> response = new Response<>();
		List<ContaCorrenteFuncionario> lista = service.buscar(new Empresas(idEmpresa));
		List<ContaCorrenteFuncionarioDto> listaDto = lista.stream()
			.map(conta -> modelMapper.map(conta, ContaCorrenteFuncionarioDto.class))
			.collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}

}
