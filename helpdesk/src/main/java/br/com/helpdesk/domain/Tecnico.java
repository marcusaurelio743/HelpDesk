package br.com.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.helpdesk.domain.dtos.TecnicoDto;
import br.com.helpdesk.domain.enums.Perfil;
@Entity
public class Tecnico extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<Chamado>();

	public Tecnico() {
		super();
		AddPerfis(Perfil.CLIENTE);
	}

	public Tecnico(Long id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		AddPerfis(Perfil.CLIENTE);
	}
	
	public Tecnico(TecnicoDto obj) {
		
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
