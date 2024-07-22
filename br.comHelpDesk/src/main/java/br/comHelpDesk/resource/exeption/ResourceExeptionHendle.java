package br.comHelpDesk.resource.exeption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
