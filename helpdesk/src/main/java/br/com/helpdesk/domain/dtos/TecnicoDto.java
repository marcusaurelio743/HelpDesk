package br.com.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpdesk.domain.Tecnico;
import br.com.helpdesk.domain.enums.Perfil;

public class TecnicoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected List<Integer> perfis = new ArrayList<>();
	@JsonFormat(pattern = "dd/M/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public TecnicoDto() { 
		super();
	}

	public TecnicoDto(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis =obj.getPerfis().stream().map(x-> x.getCodigo()).collect(Collectors.toList());
		this.dataCriacao = obj.getDataCriacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getPerfis() {
		return perfis.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toList());
	}

	public void AddPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	

}
