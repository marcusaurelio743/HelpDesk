package br.com.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.helpdesk.domain.dtos.ClienteDto;
import br.com.helpdesk.domain.enums.Perfil;
@Entity
public class Cliente extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
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
	
public Cliente(ClienteDto obj) {
		
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis =obj.getPerfis().stream().map(x-> x.getCodigo()).collect(Collectors.toList());
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
	
	
}
