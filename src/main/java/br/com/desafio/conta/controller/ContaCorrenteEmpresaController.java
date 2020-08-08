package br.com.desafio.conta.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.conta.dto.ContaCorrenteEmpresaDto;
import br.com.desafio.conta.dto.ContaDepositoDto;
import br.com.desafio.conta.dto.ContaPagamentoDto;
import br.com.desafio.conta.entity.ContaCorrenteEmpresa;
import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.conta.service.ContaCorrenteEmpresaService;
import br.com.desafio.conta.service.ContaCorrenteFuncionarioService;
import br.com.desafio.conta.service.ContaService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/conta/corrente/empresa")
public class ContaCorrenteEmpresaController {

	private ContaCorrenteEmpresaService service;
	private ModelMapper modelMapper;
	private ContaService contaService;
	private ContaCorrenteFuncionarioService contaCorrenteFuncionarioService;

	public ContaCorrenteEmpresaController(ContaCorrenteEmpresaService service, ModelMapper modelMapper,
			ContaService contaService, ContaCorrenteFuncionarioService contaCorrenteFuncionarioService) {
		this.service = service;
		this.modelMapper = modelMapper;
		this.contaService = contaService;
		this.contaCorrenteFuncionarioService = contaCorrenteFuncionarioService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteEmpresaDto>> salvar(@RequestBody @Valid ContaCorrenteEmpresaDto dto) {
		Response<ContaCorrenteEmpresaDto> response = new Response<>();
		ContaCorrenteEmpresa contaCorrente = modelMapper.map(dto, ContaCorrenteEmpresa.class);
		contaService.validarConta(contaCorrente.getAgencia(), contaCorrente.getNumero());
		ContaCorrenteEmpresa contaCorrenteSalva = service.salvar(contaCorrente);
		ContaCorrenteEmpresaDto contaCorrenteDto = modelMapper.map(contaCorrenteSalva, ContaCorrenteEmpresaDto.class);
		response.setData(contaCorrenteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("{idEmpresa}/{id}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteEmpresaDto>> buscar(@PathVariable Long idEmpresa,
			@PathVariable Long id) {
		Response<ContaCorrenteEmpresaDto> response = new Response<>();
		ContaCorrenteEmpresa contaCorrente = service.buscar(new Empresas(idEmpresa), id);
		ContaCorrenteEmpresaDto contaCorrenteDto = modelMapper.map(contaCorrente, ContaCorrenteEmpresaDto.class);
		response.setData(contaCorrenteDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("{idEmpresa}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<List<ContaCorrenteEmpresaDto>>> buscar(@PathVariable Long idEmpresa) {
		Response<List<ContaCorrenteEmpresaDto>> response = new Response<>();
		List<ContaCorrenteEmpresa> lista = service.buscar(new Empresas(idEmpresa));
		List<ContaCorrenteEmpresaDto> listaDto = lista.stream()
				.map(conta -> modelMapper.map(conta, ContaCorrenteEmpresaDto.class)).collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("transferencia/funcionario")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteEmpresaDto>> transferenciaFuncionario(@RequestBody @Valid ContaPagamentoDto dto) {
		Response<ContaCorrenteEmpresaDto> response = new Response<>();
		ContaCorrenteEmpresa contaEmpresa = service.buscar(dto.getOrigem().getAgencia(), dto.getOrigem().getNumero());
		ContaCorrenteFuncionario contaFuncionario = contaCorrenteFuncionarioService.buscar(dto.getFavorecido().getAgencia(),
				dto.getFavorecido().getNumero());
		contaEmpresa.transferir(dto.getValor(), contaFuncionario);
		ContaCorrenteEmpresa contaEmpresaAtualizada = service.atualizar(contaEmpresa);
		contaCorrenteFuncionarioService.atualizar(contaFuncionario);
		ContaCorrenteEmpresaDto contaEmpresaDto = modelMapper.map(contaEmpresaAtualizada, ContaCorrenteEmpresaDto.class);
		response.setData(contaEmpresaDto);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("depositar")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaCorrenteEmpresaDto>> depositar(@RequestBody @Valid ContaDepositoDto dto) {
		Response<ContaCorrenteEmpresaDto> response = new Response<>();
		ContaCorrenteEmpresa contaEmpresa = service.buscar(dto.getOrigem().getAgencia(), dto.getOrigem().getNumero());
		contaEmpresa.depositar(dto.getValor());
		ContaCorrenteEmpresa contaEmpresaAtualizada = service.atualizar(contaEmpresa);
		ContaCorrenteEmpresaDto contaEmpresaDto = modelMapper.map(contaEmpresaAtualizada, ContaCorrenteEmpresaDto.class);
		response.setData(contaEmpresaDto);
		return ResponseEntity.ok(response);
	}
}
