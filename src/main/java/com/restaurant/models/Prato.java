package com.restaurant.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Prato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank( message = "Preencha o campo 'Nome'" )
	private String nome;
	
	@NotNull ( message = "Preencha o campo 'Preço'" )
	private double preco;
	
	@OneToMany( mappedBy = "prato", cascade = CascadeType.ALL )
	private List<Item> itens;
	
	private boolean status;
	
	public Prato() {}
	
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
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> item) {
		this.itens = item;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
