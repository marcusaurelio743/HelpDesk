package br.comHelpDesk.resource.exeption;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String FielName;
	private String message;
	public FieldMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FieldMessage(String fielName, String message) {
		super();
		FielName = fielName;
		this.message = message;
	}
	public String getFielName() {
		return FielName;
	}
	public void setFielName(String fielName) {
		FielName = fielName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
