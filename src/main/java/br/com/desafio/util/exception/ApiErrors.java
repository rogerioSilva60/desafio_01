package br.com.desafio.util.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErrors {

	private List <String> errors;
	
	public ApiErrors(CredentialsException ex) {
		this.errors = Arrays.asList(ex.getMessage());
	}

	public ApiErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<String>();
        bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

	public ApiErrors(BusinessException ex) { 
		this.errors = Arrays.asList(ex.getMessage());
    }

	public ApiErrors(NotFoundException ex) { 
		this.errors = Arrays.asList(ex.getMessage());
    }
	
	public List<String> getErrors() {
        return errors;
    }
}
