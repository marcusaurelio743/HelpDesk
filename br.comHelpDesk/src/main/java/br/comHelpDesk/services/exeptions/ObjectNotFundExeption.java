package br.comHelpDesk.services.exeptions;

public class ObjectNotFundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFundExeption(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ObjectNotFundExeption(String message) {
		super(message);
		
	}
	
	

}
