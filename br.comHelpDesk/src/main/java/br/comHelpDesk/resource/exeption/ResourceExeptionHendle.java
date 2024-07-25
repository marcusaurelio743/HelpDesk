package br.comHelpDesk.resource.exeption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.comHelpDesk.services.exeptions.DataIntegrityViolationException;
import br.comHelpDesk.services.exeptions.ObjectNotFundExeption;

@ControllerAdvice
public class ResourceExeptionHendle {
	
	@ExceptionHandler(ObjectNotFundExeption.class)
	//essa classe é um manipulaodr de exeções
	public ResponseEntity<StandardError> objectnotfundexeption(ObjectNotFundExeption ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),
				"Object not found", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	//essa classe é um manipulaodr de exeções
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),
				"Violação de dados", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//essa classe é um manipulaodr de exeções
	public ResponseEntity<ValidationErros> ValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		ValidationErros errors = new ValidationErros(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),
				"Validation Error","Error na Validação dos campos", request.getRequestURI());
		
			for(FieldError x : ex.getBindingResult().getFieldErrors()) {
				errors.AddError(x.getField(), x.getDefaultMessage());
			}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
