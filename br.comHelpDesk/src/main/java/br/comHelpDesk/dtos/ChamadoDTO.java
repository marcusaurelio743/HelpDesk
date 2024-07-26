package br.comHelpDesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.comHelpDesk.domain.Chamado;

public class ChamadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo prioridade é requerido")
	private Integer prioridade;
	@NotNull(message = "O campo Status é requerido")
	private Integer status;
	@NotNull(message = "O campo Titulo é requerido")
	private String titulo;
	@NotNull(message = "O campo Observação é requerido")
	private String observacao;
	@NotNull(message = "O campo Cliente é requerido")
	private Long cliente;
	@NotNull(message = "O campo Tecnico é requerido")
	private Long tecnico;
	private String nomeCliente;
	private String nomeTecnico;

	public ChamadoDTO() {

	}

	public ChamadoDTO(Chamado chamado) {
		super();
		this.id = chamado.getId();
		this.dataAbertura = chamado.getDataAbertura();
		this.dataFechamento = chamado.getDataFechamento();
		this.prioridade = chamado.getPrioridade().getCodigo();
		this.status = chamado.getStatus().getCodigo();
		this.titulo = chamado.getTitulo();
		this.observacao = chamado.getObservacao();
		this.cliente = chamado.getCliente().getId();
		this.tecnico = chamado.getTecnico().getId();
		this.nomeCliente = chamado.getCliente().getNome();
		this.nomeTecnico = chamado.getTecnico().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getTecnico() {
		return tecnico;
	}

	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

}
