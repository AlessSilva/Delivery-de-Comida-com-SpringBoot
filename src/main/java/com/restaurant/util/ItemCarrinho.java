package com.restaurant.util;

import com.restaurant.models.Prato;


public class ItemCarrinho {

	private Prato prato;
	private int quantidade;
	
	public ItemCarrinho( Prato prato, int quantidade ) {
		
		this.prato = prato;
		this.quantidade = quantidade;
		
	}
	
	public Prato getPrato() {
		return prato;
	}
	public void setPrato(Prato prato) {
		this.prato = prato;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
