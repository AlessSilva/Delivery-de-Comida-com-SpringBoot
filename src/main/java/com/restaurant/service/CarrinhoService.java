package com.restaurant.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.util.Carrinho;

@Service
public class CarrinhoService {
	
	@Autowired
	PratoService pratoServ;

	public void adicionaItem(Long id, HttpSession session) {
		
		Carrinho carrinho;
		
		if( session.getAttribute("carrinho") == null ) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
		}
		
		carrinho.adicionarItem( pratoServ.buscarPrato(id) );
		session.setAttribute("carrinho", carrinho);
		
	}

	public void removeItem(Long id, HttpSession session) {
		
		Carrinho carrinho = (Carrinho)session.getAttribute("carrinho");
		carrinho.removerItem( pratoServ.buscarPrato(id) );
		session.setAttribute("carrinho", carrinho);
		
	}

	public void addQuantidade(Long id, HttpSession session) {
		
        Carrinho carrinho;
		
		if( session.getAttribute("carrinho") == null ) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
		}
		
		carrinho.aumentarQuanttidade( pratoServ.buscarPrato(id) );
		session.setAttribute("carrinho", carrinho);
		
	}

	public void subQuantidade(Long id, HttpSession session) {
		
        Carrinho carrinho;
		
		if( session.getAttribute("carrinho") == null ) {
			carrinho = new Carrinho();
		}else {
			carrinho = (Carrinho)session.getAttribute("carrinho");
		}
		
		carrinho.diminuirQuanttidade( pratoServ.buscarPrato(id) );
		session.setAttribute("carrinho", carrinho);
		
	}	
	
}
