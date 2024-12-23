package br.com.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.helpdesk.domain.enums.Perfil;
@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<Chamado>();

	public Cliente() {
		super();
		AddPerfis(Perfil.CLIENTE);
		
	}

	public Cliente(Long id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		AddPerfis(Perfil.CLIENTE);
		
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
	
	
}