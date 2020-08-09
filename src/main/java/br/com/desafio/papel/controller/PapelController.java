package br.com.desafio.papel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.papel.dto.PapelDto;
import br.com.desafio.papel.entity.Papeis;
import br.com.desafio.papel.service.PapelService;
import br.com.desafio.util.response.Response;

@RestController
@RequestMapping("api/v1/papel")
@CrossOrigin(origins = "*")
public class PapelController {

	private PapelService service;
	private ModelMapper modelMapper;
	
	public PapelController(PapelService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping
	public ResponseEntity<Response<List<PapelDto>>> buscar() {
		Response<List<PapelDto>> response = new Response<>();
		List<Papeis> lista = service.buscar();
		List<PapelDto> listaDto = lista.stream()
			.map(papel -> modelMapper.map(papel, PapelDto.class))
			.collect(Collectors.toList());
		response.setData(listaDto);
		return ResponseEntity.ok(response);
	}
}
