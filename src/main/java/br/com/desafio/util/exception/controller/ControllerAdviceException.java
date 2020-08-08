package br.com.desafio.util.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.com.desafio.util.exception.ApiErrors;
import br.com.desafio.util.exception.BusinessException;
import br.com.desafio.util.exception.CredentialsException;
import br.com.desafio.util.exception.NotFoundException;
import br.com.desafio.util.response.Response;


@RestControllerAdvice
public class ControllerAdviceException {

	@ExceptionHandler(CredentialsException.class)
	public ResponseEntity handleCredentialsException(CredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getResponseException(new ApiErrors(ex)));
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity handleBusinessException(BusinessException ex) {
		return ResponseEntity.badRequest().body(getResponseException(new ApiErrors(ex)));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleValidationException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		return ResponseEntity.badRequest().body(getResponseException(new ApiErrors(bindingResult)));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getResponseException(new ApiErrors(ex)));
	}
	
	private Response getResponseException(ApiErrors apiErrors) {
		Response response = new Response<>();
		response.setErrors(apiErrors.getErrors());
		return response;
	}
}
