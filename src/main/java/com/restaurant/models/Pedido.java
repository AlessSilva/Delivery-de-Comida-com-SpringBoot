package com.restaurant.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataSolicitacao;
	
	@NotNull
	private Double valorTotal;
	
	@NotBlank
	private String tipoPagamento;
	
	@OneToMany( mappedBy = "pedido", cascade = CascadeType.ALL )
	private List<Item> itens;
	
	@ManyToOne ( cascade = CascadeType.ALL )
	private Cliente cliente;

	@OneToOne
	private Endereco endereco;
	
	public Pedido() {}
	
	public Pedido( Cliente cliente, Date dataSolicitacao, Double valorTotal, String tipoPagamento,
			List<Item> itens) {
		this.cliente = cliente;
		this.dataSolicitacao = dataSolicitacao;
		this.valorTotal = valorTotal;
		this.tipoPagamento = tipoPagamento;
		this.itens = itens;
	}
	public Pedido(  Cliente cliente, Date dataSolicitacao, Double valorTotal, String tipoPagamento, Endereco end) {
		this.cliente = cliente;
		this.dataSolicitacao = dataSolicitacao;
		this.valorTotal = valorTotal;
		this.tipoPagamento = tipoPagamento;
		this.endereco = end;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setPratos(List<Item> itens) {
		this.itens = itens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
