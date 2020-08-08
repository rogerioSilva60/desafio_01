package br.com.desafio.publico.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PublicoController {

	@GetMapping
	public String initial() {
		return "Servidor desafio esta rodando";
	}
}
