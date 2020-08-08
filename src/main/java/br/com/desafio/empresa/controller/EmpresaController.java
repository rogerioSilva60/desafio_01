package br.com.desafio.empresa.controller;

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

import br.com.desafio.empresa.dto.EmpresaDto;
import br.com.desafio.empresa.entity.Empresas;
import br.com.desafio.empresa.service.EmpresaService;
import br.com.desafio.util.UtilController;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/empresa")
public class EmpresaController extends UtilController {

	private EmpresaService service;
	private ModelMapper modelMapper;
	
	public EmpresaController(EmpresaService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<EmpresaDto>> salvar(@RequestBody @Valid EmpresaDto dto) {
		Response<EmpresaDto> response = new Response<>();
		Empresas empresa = modelMapper.map(dto, Empresas.class);
		Empresas empresaSalva = service.salvar(empresa, getIdUser());
		EmpresaDto empresaDto = modelMapper.map(empresaSalva, EmpresaDto.class);
		response.setData(empresaDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAnyRole('ADM')")
	public ResponseEntity<Response<EmpresaDto>> buscar(@PathVariable Long id) {
		Response<EmpresaDto> response = new Response<>();
		Empresas empresa = service.buscar(id, getIdUser());
		EmpresaDto empresaDto = modelMapper.map(empresa, EmpresaDto.class);
		response.setData(empresaDto);
		return ResponseEntity.ok(response);
	}
}
