package com.gregory.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gregory.course.services.exceptions.DatabaseException;
import com.gregory.course.services.exceptions.ResourceNotFoundException;

// class para fazer o tratament personalizado das exceções tratadas
@ControllerAdvice// faz com que intercepte as excessoes que acontecerem para esta classe exacutar o tratamento
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class) //anotation para o metodo abaixo intercepte qualquer exceção que acontecer da ResourceNotFoundException
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String erro = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class) //anotation para o metodo abaixo intercepte qualquer exceção que acontecer da DatabaseException
	public ResponseEntity<StandardError> resourceNotFound(DatabaseException e, HttpServletRequest request){
		String erro = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), erro, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
