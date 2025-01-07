package br.com.helpdesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.helpdesk.services.exceptions.ObjectNotFundException;

@ControllerAdvice
public class ResourceExeptionHendle {
	
	@ExceptionHandler(ObjectNotFundException.class)
	public ResponseEntity<StandardError> objectNotFundException(ObjectNotFundException ex,
			HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object Not Found!!", ex.getMessage(), request.getRequestURI());
		
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
			HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Violação de dados", ex.getMessage(), request.getRequestURI());
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request){
		
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos", "Error nos seguintes dados:",request.getRequestURI());
			
		for(FieldError x: ex.getBindingResult().getFieldErrors()) {
				errors.AddErrors(x.getField(), x.getDefaultMessage());
			}
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
		
	}

}
