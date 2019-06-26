package com.restaurant.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Prato prato;
	
	@ManyToOne
	private Pedido pedido;
	
	@NotNull
	private int quantidade;
	
	public Item() {
		
	}
	
	public Item( Prato prato, int quantidade ) {
		this.prato = prato;
		this.quantidade = quantidade;
	}
	
	public Item( Prato prato, int quantidade, Pedido pedido ) {
		this.prato = prato;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}
	
	public Prato getPrato() {
		return prato;
	}

	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
