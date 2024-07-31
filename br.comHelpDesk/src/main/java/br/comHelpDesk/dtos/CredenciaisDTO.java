package br.comHelpDesk.dtos;

public class CredenciaisDTO {
	private String Login;
	private String senha;
	private String email;

	public CredenciaisDTO() {
		
	}

	

	public CredenciaisDTO(String login, String senha, String email) {
		
		Login = login;
		this.senha = senha;
		this.email = email;
	}
	



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
