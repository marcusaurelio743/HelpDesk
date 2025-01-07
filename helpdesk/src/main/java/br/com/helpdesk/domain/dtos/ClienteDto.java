package br.com.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helpdesk.domain.Cliente;
import br.com.helpdesk.domain.enums.Perfil;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Long id;
	@NotNull(message =  "O campo Nome é requerido")
	protected String nome;
	@NotNull(message =  "O campo CPF é requerido")
	protected String cpf;
	@NotNull(message =  "O campo Email é requerido")
	protected String email;
	@NotNull(message =  "O campo Senha é requerido")
	protected String senha;
	protected List<Integer> perfis = new ArrayList<>();
	@JsonFormat(pattern = "dd/M/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public ClienteDto() { 
		super();
		AddPerfis(Perfil.CLIENTE);
	}

	public ClienteDto(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis =obj.getPerfis().stream().map(x-> x.getCodigo()).collect(Collectors.toList());
		this.dataCriacao = obj.getDataCriacao();
		AddPerfis(Perfil.CLIENTE);
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
