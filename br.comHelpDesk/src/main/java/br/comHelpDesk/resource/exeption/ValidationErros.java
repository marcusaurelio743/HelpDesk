package br.comHelpDesk.resource.exeption;

import java.util.ArrayList;
import java.util.List;

public class ValidationErros extends StandardError{

	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<FieldMessage>();

	public ValidationErros() {
		super();
		
	}

	public ValidationErros(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void AddError(String nameField, String message) {
		this.errors.add(new FieldMessage(nameField, message));
	}
	
	
	

}
