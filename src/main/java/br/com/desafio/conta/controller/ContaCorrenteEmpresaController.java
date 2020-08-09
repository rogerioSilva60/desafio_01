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
import br.com.desafio.conta.dto.ContaDto;
import br.com.desafio.conta.dto.ContaPagamentoDto;
import br.com.desafio.conta.entity.ContaCorrenteFuncionario;
import br.com.desafio.conta.entity.Contas;
import br.com.desafio.conta.service.ContaCorrenteFuncionarioService;
import br.com.desafio.conta.service.ContaService;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.empresa.service.EmpresaService;
import br.com.desafio.usuario.entity.Usuarios;
import br.com.desafio.util.UtilController;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/conta/corrente/empresa")
public class ContaCorrenteEmpresaController extends UtilController {

	private ModelMapper modelMapper;
	private ContaService service;
	private ContaCorrenteFuncionarioService contaCorrenteFuncionarioService;
	private EmpresaService empresaService;

	public ContaCorrenteEmpresaController(ContaService service, ModelMapper modelMapper,
			ContaCorrenteFuncionarioService contaCorrenteFuncionarioService, EmpresaService empresaService) {
		this.service = service;
		this.modelMapper = modelMapper;
		this.contaCorrenteFuncionarioService = contaCorrenteFuncionarioService;
		this.empresaService = empresaService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaDto>> salvar(@RequestBody @Valid ContaDto dto) {
		Response<ContaDto> response = new Response<>();
		Contas conta = modelMapper.map(dto, Contas.class);
		service.validarConta(conta.getAgencia(), conta.getNumero());
		Contas contaSalva = service.salvar(conta);
		ContaDto contaCorrenteDto = modelMapper.map(contaSalva, ContaDto.class);
		response.setData(contaCorrenteDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<List<ContaDto>>> buscar() {
		Response<List<ContaDto>> response = new Response<>();
		List<Empresas> empresas = empresaService.buscar(new Usuarios(getIdUser()));
		List<Contas> lista = service.buscarSemFuncionarios(empresas);
		List<ContaDto> listaDto = lista.stream().map(conta -> modelMapper.map(conta, ContaDto.class))
				.collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}

	@GetMapping("{idEmpresa}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<List<ContaDto>>> buscar(@PathVariable Long idEmpresa) {
		Response<List<ContaDto>> response = new Response<>();
		List<Contas> lista = service.buscarSemFuncionarios(new Empresas(idEmpresa));
		List<ContaDto> listaDto = lista.stream().map(conta -> modelMapper.map(conta, ContaDto.class))
				.collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("transferencia/funcionario")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaDto>> transferenciaFuncionario(@RequestBody @Valid ContaPagamentoDto dto) {
		Response<ContaDto> response = new Response<>();
		Contas contaEmpresa = service.buscar(dto.getOrigem().getAgencia(), dto.getOrigem().getNumero());
		ContaCorrenteFuncionario contaFuncionario = contaCorrenteFuncionarioService
				.buscar(dto.getFavorecido().getAgencia(), dto.getFavorecido().getNumero());
		contaEmpresa.transferir(dto.getValor(), contaFuncionario);
		Contas contaEmpresaAtualizada = service.atualizar(contaEmpresa);
		contaCorrenteFuncionarioService.atualizar(contaFuncionario);
		ContaDto contaEmpresaDto = modelMapper.map(contaEmpresaAtualizada, ContaDto.class);
		response.setData(contaEmpresaDto);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("depositar")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<ContaDto>> depositar(@RequestBody @Valid ContaDepositoDto dto) {
		Response<ContaDto> response = new Response<>();
		Contas contaEmpresa = service.buscar(dto.getOrigem().getAgencia(), dto.getOrigem().getNumero());
		contaEmpresa.depositar(dto.getValor());
		Contas contaEmpresaAtualizada = service.atualizar(contaEmpresa);
		ContaDto contaEmpresaDto = modelMapper.map(contaEmpresaAtualizada, ContaDto.class);
		response.setData(contaEmpresaDto);
		return ResponseEntity.ok(response);
	}
}
