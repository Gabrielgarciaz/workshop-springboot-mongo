package com.gabrielgarcia.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabrielgarcia.workshopmongo.service.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Responsável por tratar possíveis erros nas requisisões
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)// Identificar para quando ocorrer essa exceção é para fazer esse tratamento
	public ResponseEntity<StandardError> objNotFound(ObjectNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
