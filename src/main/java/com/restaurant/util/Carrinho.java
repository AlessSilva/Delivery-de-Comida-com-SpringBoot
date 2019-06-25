package com.restaurant.util;

import java.util.ArrayList;
import java.util.List;

import com.restaurant.models.*;

import com.restaurant.models.Prato;


public class Carrinho {
	
	List<Item> itens;

	public Carrinho() {
		itens = new ArrayList<Item>();
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public void adicionarItem( Prato prato ) {
		
		Item item = procuraPrato(prato);
		
		if( item != null ) {
			
			int index = itens.indexOf(item);
			itens.get(index).setQuantidade( itens.get(index).getQuantidade() + 1 );
		
		}else {
		
			item = new Item(prato, 1);
			itens.add(item);
			
		}
		
		System.out.println(item.getPrato());
		
	}
	
	public void aumentarQuanttidade( Prato prato ) {
		
		Item item = procuraPrato(prato);
		int index = itens.indexOf(item);
		itens.get(index).setQuantidade( itens.get(index).getQuantidade() + 1 );
	}
	
    public void diminuirQuanttidade( Prato prato ) {
		
		Item item = procuraPrato(prato);
		int index = itens.indexOf(item);
		itens.get(index).setQuantidade( itens.get(index).getQuantidade() - 1 );
		if( itens.get(index).getQuantidade() <= 0 ) {
			removerItem(prato);
		}
	}
	
	public void removerItem( Prato prato ) {
		
		itens.remove( procuraPrato(prato) );
		
	}
	
	private Item procuraPrato( Prato prato ) {
		
		for( Item i : itens ) {
			
			if( i.getPrato().getId().equals(prato.getId()))  {
				return i;
			}
			
		}
		
		return null;
		
	}
	
	public double precoTotal() {
		
		double soma = 0;
		
		for( Item i : itens ) {
			
			soma += i.getPrato().getPreco() * i.getQuantidade();
			
		}
		
		return soma;
		
	}
	
}