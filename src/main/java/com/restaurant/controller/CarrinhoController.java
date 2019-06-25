package com.restaurant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.restaurant.service.CarrinhoService;
import com.restaurant.service.ClienteService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	CarrinhoService carrinhoServ;
	
	@Autowired
	ClienteService clienteServ;

	@RequestMapping("/listar")
	public ModelAndView listar() {
		
		ModelAndView mv = new ModelAndView("listagemCarrinho");
	
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String email = user.getUsername();
		
		mv.addObject( "endereco" , clienteServ.buscarClientePorEmail(email).getEndereco());
		
		return mv;
	}
	
	@RequestMapping("/adicionar/{id}")
	public ModelAndView adicionar( @PathVariable Long id, HttpSession session ) {
		
		carrinhoServ.adicionaItem( id, session );
		
		ModelAndView mv = new ModelAndView("redirect:/prato/listar");
		
		return mv;
		
	}
	
	@RequestMapping("/remover/{id}")
	public ModelAndView remover( @PathVariable Long id, HttpSession session ) {
		
		carrinhoServ.removeItem(id, session);
		
		ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
		
		return mv;
	}
	
	@RequestMapping("/aumentar/{id}")
	public ModelAndView aumentar( @PathVariable Long id, HttpSession session ) {
		
		carrinhoServ.addQuantidade(id, session);
		
		ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
		
		return mv;
	}
	
	@RequestMapping("/diminuir/{id}")
	public ModelAndView diminuir( @PathVariable Long id, HttpSession session ) {
		
		carrinhoServ.subQuantidade(id, session);
		
		ModelAndView mv = new ModelAndView("redirect:/carrinho/listar");
		
		return mv;
	}
	
}
